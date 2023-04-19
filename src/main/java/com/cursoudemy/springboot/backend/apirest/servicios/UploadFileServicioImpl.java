package com.cursoudemy.springboot.backend.apirest.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class UploadFileServicioImpl implements UploadFileServicioInterfaz{

    private final Logger log = LoggerFactory.getLogger(UploadFileServicioImpl.class);

    private final static String DIRECTORIO_UPLOADS = "uploads";

    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {

        Path rutaArchivo = getPath(nombreFoto);

        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() && !recurso.isReadable()){
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();

            recurso = new UrlResource(rutaArchivo.toUri());

            log.error("Error, no se pudo cargar la imagen");
        }

        return recurso;

    }

    @Override
    public String copiar(MultipartFile archivo) throws IOException {

        String nombreArchivo = UUID.randomUUID() + "_" +archivo.getOriginalFilename().replace(" ", "").replace("♥", "");

        Path rutaArchivo = getPath(nombreArchivo);
        log.info(rutaArchivo.toString());

        Files.copy(archivo.getInputStream(), rutaArchivo);


        return nombreArchivo;

    }

    @Override
    public void eliminar(String nombreFoto) {

        if (nombreFoto != null && nombreFoto.length() > 0){
            Path rutaFotoAnterior = getPath(nombreFoto);
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                archivoFotoAnterior.delete();
            }
        }

    }

    @Override
    public Path getPath(String nombreFoto) {

        return Paths.get(DIRECTORIO_UPLOADS).resolve(nombreFoto).toAbsolutePath();

    }

}
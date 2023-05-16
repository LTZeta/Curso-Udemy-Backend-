package com.cursoudemy.springboot.backend.apirest.servicios;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface UploadFileServicio {

    public Resource cargar(String nombreFoto) throws MalformedURLException;

    public String copiar(MultipartFile archivo) throws IOException;

    public void eliminar(String nombreFoto);

    public Path getPath(String nombreFoto);

}
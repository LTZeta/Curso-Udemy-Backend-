package com.cursoudemy.springboot.backend.apirest.auth;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAve6DUrEbLL+uVicbVPZ2ZE0zc+6U+ma4F0zLN6xN/8U4ceD3\n" +
            "OaEO5JX37NDWIETLu+NydO93fXf1jjS0mZvvh/Cu6n3A93xk3S5/1RWEZmTGfdWw\n" +
            "pPiCyf3sqxxEmzRB1L591FFT2CbCyPd5kR5DeEyWJpGV5kaGmLizVxJVkAHRimt6\n" +
            "xi8+CIQGc2DJQoGsLTLp0Iz5AYSa1pyaQk3vYKyrrwngdVcysbuvbeod4+1b0gbT\n" +
            "cA2d4LjJ7QxAszYwSYTQy/CQiTxl36viT/UwQBT4xqPkqXutz+aXQ5NxPr+qxReZ\n" +
            "pEYLlNIEwNTySsAs1/vH+Ct52J7tBdDbsYF66wIDAQABAoIBAQCEq9453hMAcCjE\n" +
            "b27NK5hJug+t9MNGIoiHZhIyIn8dodMq4R1wuQthK0zStJJOWiACb/+cpUXPdAMo\n" +
            "QbLB9kYcrEhS7mLjvZXrVrbezrf8mnp4ndNVeW5K0MAISqe1zowJosLck67At1qq\n" +
            "7QuTDhPNLZpY+Ks//pW4ipRjxx/The0y9IUeDvoflhvp94pq4xTFps5d2i5KldQs\n" +
            "Zp1O35BR8yyX/2PLnZtTtbIDXVJURQaKsScB1/yZ9/9JqLkxAZZJTkJ0Xv3VJ45T\n" +
            "yZmJrbBG0yAlqw1Alz6IohCE4KMuVU++FsJAAT8uMkZJEhggIjVnqVAhx8y4kQBf\n" +
            "YnSdmctJAoGBAOg5YWXWbVCSRE2g/nsnZcX1Em8IQATm0pXSBAm+SUehLFK9nxEJ\n" +
            "N+7y7Obb2qMtzrZgMzSLHWX0XSRiacspJJ43S/1mrx34nvGL6Gkbl7etFmq7Baox\n" +
            "k67JVVAYgu8Bqd1MZARwBCeIWMaWPN+B+guGfedIdDjx8SDOlfzFOal1AoGBANFg\n" +
            "pJLpamNZ35XtQcvRsuvHflTabVS1dhLlbrWa8kovHGnylghZiCOON89ZCq1tpPJh\n" +
            "0Xa80LATNVyfaL0Naq8034E3YZyvBFziHbYKDdzCVD+EU+M+vE9lUQByCdCPvp2z\n" +
            "oMkzXY81cExkBcn5XJfkwsSJCpbsDIFuPu+tMabfAoGBAJfftxgwJWv1TOBsMyJ+\n" +
            "AKx3e+fLwHq0LvM219c7cJd9DAzM4LP2kHMgwti03oer1K4nujVZ6g8cwrGh/0iU\n" +
            "DtO+nF8MgeibCfA1LPlYx1+jvq5sw+ar58b++2GXtkHzl5EYfaz9SWpFXLH73bHb\n" +
            "Qpm5CqxxETmQ2b83/JXEstJlAoGAfVT6bq6mbn5X5mk1X4pRLe4m4yg1FeXhngIU\n" +
            "uUTdECcPPSRnRuxBH/d71PSZ/aKKgESpnAf+a8ezw5PwoGotmF9wAuth1Oa3F9qK\n" +
            "qnNuRN1WjQbrKDZfhq9BQ6D/2waQtdnWv1muAZGdfTzKclKAaLQZGohl/F2kRr31\n" +
            "kob8F8UCgYAlbma8mg9zv+1CO2ZSFq2gMqHbS55i69Ujgm2SyJNWua83hc1iaXpC\n" +
            "xKytlFtbrEBbPZWuO0f42M/gQu+tVBtJfRzPhQr0z8NY2UcN/j0MLYLqNHtPhjWj\n" +
            "II2qzGg7y7m2ODfoC4MmIy98slL0EuFy2mT0Qd5YGe8UzEE9XUyOWg==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAve6DUrEbLL+uVicbVPZ2\n" +
            "ZE0zc+6U+ma4F0zLN6xN/8U4ceD3OaEO5JX37NDWIETLu+NydO93fXf1jjS0mZvv\n" +
            "h/Cu6n3A93xk3S5/1RWEZmTGfdWwpPiCyf3sqxxEmzRB1L591FFT2CbCyPd5kR5D\n" +
            "eEyWJpGV5kaGmLizVxJVkAHRimt6xi8+CIQGc2DJQoGsLTLp0Iz5AYSa1pyaQk3v\n" +
            "YKyrrwngdVcysbuvbeod4+1b0gbTcA2d4LjJ7QxAszYwSYTQy/CQiTxl36viT/Uw\n" +
            "QBT4xqPkqXutz+aXQ5NxPr+qxReZpEYLlNIEwNTySsAs1/vH+Ct52J7tBdDbsYF6\n" +
            "6wIDAQAB\n" +
            "-----END PUBLIC KEY-----";

}
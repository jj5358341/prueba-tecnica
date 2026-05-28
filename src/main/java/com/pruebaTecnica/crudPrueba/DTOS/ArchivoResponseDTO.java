
package com.pruebaTecnica.crudPrueba.DTOS;


public class ArchivoResponseDTO {
    
        private int status;
    private String message;
    private String fileName;
    private String fileBase64;

    public ArchivoResponseDTO() {
    }

    public ArchivoResponseDTO(int status, String message, String fileName, String fileBase64) {
        this.status = status;
        this.message = message;
        this.fileName = fileName;
        this.fileBase64 = fileBase64;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }
}

package com.springmvc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaFileService {

    private String uploadPath = "/usr/local/tomcat/uploads/";

    public boolean isUploadFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }

    public boolean isUploadFile(MultipartFile[] files) {
        return files != null && files.length > 0;
    }

    private String generateUniqueFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }

    public String saveFile(MultipartFile file) throws IOException {
        // Check that the file from the client request is not empty
        if (isUploadFile(file) && file.getOriginalFilename() != null) {
            try {
                String fileName = generateUniqueFileName(file.getOriginalFilename());
                String relativePath = "uploads/" + fileName;
                String path = uploadPath + File.separator + fileName;

                // Save file to path
                File newFile = new File(path);
                file.transferTo(newFile);

                return relativePath;
            } catch (IOException e) {
                return null;
            }
        }

        return null;
    }

    public List<String> saveAllFiles(MultipartFile[] files) throws IOException {
        List<String> filePaths = new ArrayList<>();

        // Check that the file from the client request is not empty
        if (isUploadFile(files)) {
            for (MultipartFile file : files) {
                String filePath = saveFile(file);
                filePaths.add(filePath);
            }
        }

        return filePaths;
    }

    public boolean deleteFile(String filePath) {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String path = uploadPath + File.separator + fileName;
        File file =  new File(path);

        if (file.exists())
            return file.delete();   // True if delete success
        return false;
    }
}

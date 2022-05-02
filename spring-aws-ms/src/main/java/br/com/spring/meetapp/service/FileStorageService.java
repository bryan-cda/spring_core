package br.com.spring.meetapp.service;

import br.com.spring.meetapp.config.FileStorageConfig;
import br.com.spring.meetapp.exeption.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path storageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.storageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(storageLocation);

        } catch (Exception e) {
            throw new FileStorageException("Can't create directory where file will be created.", e);
        }
    }
    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new FileStorageException(String.format("Can't create file with this invalid path sequence: %s", fileName));
            }
            Path targetLocation = this.storageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new FileStorageException(String.format("Could not store file: %s", fileName));
        }
        return fileName;
    }
}

package br.com.spring.meetapp.controller;

import br.com.spring.meetapp.service.FileStorageService;
import br.com.spring.meetapp.vo.response.UploadFileResponseVO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApiResponse(description = "File Endpoint")
@RestController
@RequestMapping("/api/v1/file")
@Slf4j
public class FileController {

    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService){
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadFileResponseVO> uploadFile(@RequestParam (name = "file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/")
                .path(fileName)
                .toUriString();

        UploadFileResponseVO uploadFileResponse = UploadFileResponseVO.builder()
                .fileName(fileName)
                .fileURI(fileDownloadURI)
                .fileSize(file.getSize())
                .fileType(file.getContentType())
                .build();
        return ResponseEntity.ok(uploadFileResponse);
    }

    @PostMapping("/upload-many-files")
    public List<ResponseEntity<UploadFileResponseVO>> uploadManyFiles(@RequestParam (name = "files") MultipartFile[] file){
        return Arrays.asList(file).stream().map(files -> uploadFile(files)).collect(Collectors.toList());
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            log.info("Could not determine file type!");
        }

        if (Objects.isNull(contentType)) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}

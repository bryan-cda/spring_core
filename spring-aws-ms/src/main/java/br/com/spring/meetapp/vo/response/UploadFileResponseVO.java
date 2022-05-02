package br.com.spring.meetapp.vo.response;

import lombok.Data;

@Data
public class UploadFileResponseVO {
    private String fileName;
    private String fileURI;
    private Long fileSize;
    private String fileType;
}

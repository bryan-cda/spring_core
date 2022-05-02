package br.com.spring.meetapp.vo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadFileResponseVO {
    private String fileName;
    private String fileURI;
    private Long fileSize;
    private String fileType;
}

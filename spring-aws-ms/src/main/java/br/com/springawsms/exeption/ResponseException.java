package br.com.springawsms.exeption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ResponseException implements Serializable {
    private static final long serIalVersionUID = 1L;
    private Date timestamp;
    private String message;
    private String details;
}

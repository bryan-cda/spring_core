package br.com.springawsms.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode
public class AccountCredentialsVO implements Serializable {
    private String userName;
    private String password;
}

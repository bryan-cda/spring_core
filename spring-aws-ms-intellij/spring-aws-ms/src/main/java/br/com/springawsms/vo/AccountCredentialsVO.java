package br.com.springawsms.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCredentialsVO  {
    private String userName;
    private String password;
}

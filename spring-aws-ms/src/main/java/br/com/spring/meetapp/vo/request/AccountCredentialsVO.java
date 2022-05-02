package br.com.spring.meetapp.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCredentialsVO  {
    private String username;
    private String password;
}

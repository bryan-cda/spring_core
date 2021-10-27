package br.com.springawsms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "permission")
@EqualsAndHashCode
public class Permission implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String description;


    @Override
    public String getAuthority() {
        return this.description;
    }
}

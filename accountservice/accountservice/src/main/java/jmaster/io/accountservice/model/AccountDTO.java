package jmaster.io.accountservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id ;
    private String name ;
    private String password;
    private String username;
    private Set<String> roles ;
}

package br.com.alinson.testeunimed.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String login;
    private String senha;
}

package com.example.alertasaude_backend.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 1, message = "A idade deve ser maior ou igual a 1")
    private int idade;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;
}

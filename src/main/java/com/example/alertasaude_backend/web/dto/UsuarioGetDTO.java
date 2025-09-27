package com.example.alertasaude_backend.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioGetDTO {

    @NotNull
    private int id;
}

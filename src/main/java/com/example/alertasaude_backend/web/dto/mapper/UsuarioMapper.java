package com.example.alertasaude_backend.web.dto.mapper;

import com.example.alertasaude_backend.entity.Usuario;
import com.example.alertasaude_backend.web.dto.UsuarioCreateDTO;
import com.example.alertasaude_backend.web.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDTO createDTO){
        return new ModelMapper().map(createDTO, Usuario.class);
    }

    public static UsuarioResponseDTO toDTO(Usuario usuario){
        return new ModelMapper().map(usuario, UsuarioResponseDTO.class);
    }

    public static List<UsuarioResponseDTO> toListDTO(List<Usuario> usuarios){
        return usuarios.stream().map(usuario -> toDTO(usuario)).collect(Collectors.toList());
    }
}

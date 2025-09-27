package com.example.alertasaude_backend.web.dto.mapper;

import com.example.alertasaude_backend.entity.Medicamento;
import com.example.alertasaude_backend.web.dto.MedicamentoCreateDTO;
import com.example.alertasaude_backend.web.dto.MedicamentoResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MedicamentoMapper {

    public static Medicamento toMedicamento(MedicamentoCreateDTO createDTO){
        return new ModelMapper().map(createDTO, Medicamento.class);
    }

    public static MedicamentoResponseDTO toDTO(Medicamento medicamento){
        return new ModelMapper().map(medicamento, MedicamentoResponseDTO.class);
    }

    public static List<MedicamentoResponseDTO> toListDTO(List<Medicamento> medicamentos){
        return medicamentos.stream().map(medicamento -> toDTO(medicamento)).collect(Collectors.toList());
    }
}

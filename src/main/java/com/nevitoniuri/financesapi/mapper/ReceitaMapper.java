package com.nevitoniuri.financesapi.mapper;

import com.nevitoniuri.financesapi.controller.request.ReceitaRequest;
import com.nevitoniuri.financesapi.model.Receita;
import com.nevitoniuri.financesapi.model.dto.ReceitaDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ReceitaMapper {
    ReceitaMapper INSTANCE = getMapper(ReceitaMapper.class);

    ReceitaDTO toDTO(Receita receita);

    Receita toEntity(ReceitaRequest receitaRequest);
}

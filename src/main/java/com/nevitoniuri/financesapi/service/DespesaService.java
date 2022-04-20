package com.nevitoniuri.financesapi.service;

import com.nevitoniuri.financesapi.mapper.DespesaMapper;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.nevitoniuri.financesapi.mapper.DespesaMapper.*;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    private static final DespesaMapper despesaMapper = INSTANCE;

    public List<DespesaDTO> listarDespesas() {
        List<Despesa> despesas = despesaRepository.findAll();
        return despesas.stream().map(despesaMapper::toDTO).collect(Collectors.toList());
    }
}

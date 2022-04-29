package com.nevitoniuri.financesapi.service;

import com.nevitoniuri.financesapi.exception.BadRequestException;
import com.nevitoniuri.financesapi.mapper.DespesaMapper;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.nevitoniuri.financesapi.mapper.DespesaMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private static final DespesaMapper despesaMapper = INSTANCE;

    public List<DespesaDTO> listarDespesas() {
        List<Despesa> despesas = despesaRepository.findAll();
        return despesas.stream().map(despesaMapper::toDTO).collect(Collectors.toList());
    }

    public List<DespesaDTO> listarDespesasPorDescricao(String descricao) {
        List<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);
        return despesas.stream().map(despesaMapper::toDTO).collect(Collectors.toList());
    }

    public DespesaDTO salvarDespesa(DespesaDTO despesaDTO) {
        Despesa despesaSalva = despesaRepository.save(despesaMapper.toEntity(despesaDTO));
        return despesaMapper.toDTO(despesaSalva);
    }

    public DespesaDTO buscarPorId(Long id) {
        return despesaMapper.toDTO(despesaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Despesa não encontrada")));
    }
}

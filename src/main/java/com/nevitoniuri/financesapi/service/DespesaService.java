package com.nevitoniuri.financesapi.service;

import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.exception.BadRequestException;
import com.nevitoniuri.financesapi.mapper.DespesaMapper;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nevitoniuri.financesapi.mapper.DespesaMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private static final String NAO_ENCONTRADO = "Despesa não encontrada";
    private static final DespesaMapper despesaMapper = INSTANCE;
    private final DespesaRepository despesaRepository;

    public List<DespesaDTO> listar(String descricao) {
        List<Despesa> despesas;
        if (descricao == null) {
            despesas = despesaRepository.findAll();
        } else {
            despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao);
        }
        return despesas.stream().map(despesaMapper::toDTO).collect(Collectors.toList());
    }

    public DespesaDTO salvar(DespesaRequest despesaRequest) {
        if (isExistente(despesaRequest.getDescricao())) {
            throw new BadRequestException("Despesa já cadastrada");
        }
        Despesa despesaSalva = despesaRepository.save(despesaMapper.toEntity(despesaRequest));
        return despesaMapper.toDTO(despesaSalva);
    }

    public DespesaDTO atualizar(Long id, DespesaRequest despesaRequest) {
        Optional<Despesa> despesaBuscada = despesaRepository.findById(id);
        if (despesaBuscada.isPresent()) {
            despesaBuscada.get().setDescricao(despesaRequest.getDescricao());
            despesaBuscada.get().setValor(despesaRequest.getValor());
            despesaBuscada.get().setData(despesaRequest.getData());
            return despesaMapper.toDTO(despesaRepository.saveAndFlush(despesaBuscada.get()));
        } else {
            throw new BadRequestException(NAO_ENCONTRADO);
        }
    }

    public boolean isExistente(String descricao) {
        return despesaRepository.existsByDescricao(descricao);
    }

    public DespesaDTO buscarPorIdToDTO(Long id) {
        return despesaMapper.toDTO(despesaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(NAO_ENCONTRADO)));
    }

    public Despesa buscarPorIdEntity(Long id) {
        return despesaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(NAO_ENCONTRADO));
    }

    public void deletar(Long id) {
        despesaRepository.delete(buscarPorIdEntity(id));
    }
}

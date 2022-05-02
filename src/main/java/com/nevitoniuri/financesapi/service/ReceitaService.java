package com.nevitoniuri.financesapi.service;

import com.nevitoniuri.financesapi.controller.request.ReceitaRequest;
import com.nevitoniuri.financesapi.exception.ReceitaDuplicadaException;
import com.nevitoniuri.financesapi.exception.ReceitaNaoEncontradaException;
import com.nevitoniuri.financesapi.mapper.ReceitaMapper;
import com.nevitoniuri.financesapi.model.Receita;
import com.nevitoniuri.financesapi.model.dto.ReceitaDTO;
import com.nevitoniuri.financesapi.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nevitoniuri.financesapi.mapper.ReceitaMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private static final ReceitaMapper receitaMapper = INSTANCE;
    private final ReceitaRepository receitaRepository;

    public Page<ReceitaDTO> listar (String descricao, Pageable pageable) {
        List<Receita> receitas;
        if (descricao == null) {
            receitas = receitaRepository.findAll(pageable).getContent();
        } else {
            receitas = receitaRepository.findByDescricaoContainingIgnoreCase(descricao, pageable).getContent();
        }
        List<ReceitaDTO> collect = receitas.stream().map(receitaMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, receitas.size());
    }

    public ReceitaDTO buscarPorId (Long id) {
        Receita receitaBuscada = checarSeExistePorId(id);
        return receitaMapper.toDTO(receitaBuscada);
    }

    public Page<ReceitaDTO> buscarPeloAnoMes(Integer ano, Integer mes, Pageable pageable) {
        List<Receita> receitasEncontradas = receitaRepository.findAll(pageable).getContent();
        List<Receita> receitasDentroDoRangeDesejado = new ArrayList<>();
        for (Receita receitaEncontrada : receitasEncontradas) {
            LocalDate receita = receitaEncontrada.getData();
            if (receita.getYear() == ano && receita.getMonthValue() == mes) {
                receitasDentroDoRangeDesejado.add(receitaEncontrada);
            }
        }
        List<ReceitaDTO> collect = receitasDentroDoRangeDesejado.stream().map(receitaMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, receitasDentroDoRangeDesejado.size());
    }

    @Transactional
    public ReceitaDTO cadastrar(ReceitaRequest receitaRequest) {
        checarSeExisteNoMesmoMes(receitaRequest);
        Receita receitaSalva = receitaRepository.save(receitaMapper.toEntity(receitaRequest));
        return receitaMapper.toDTO(receitaSalva);
    }

    @Transactional
    public ReceitaDTO atualizar(Long id, ReceitaRequest receitaRequest) {
        Receita receitaBuscada = checarSeExistePorId(id);
        receitaBuscada.setDescricao(receitaRequest.getDescricao());
        receitaBuscada.setValor(receitaRequest.getValor());
        receitaBuscada.setData(receitaRequest.getData());
        return receitaMapper.toDTO(receitaRepository.saveAndFlush(receitaBuscada));
    }

    @Transactional
    public void deletar(Long id) {
        Receita receitaBuscada = checarSeExistePorId(id);
        receitaRepository.delete(receitaBuscada);
    }

    private void checarSeExisteNoMesmoMes(ReceitaRequest receitaRequest) {
        Optional<Receita> receitaBuscada = receitaRepository.findByDescricaoIgnoreCase(receitaRequest.getDescricao());
        if (receitaBuscada.isPresent() && receitaBuscada.get().getData().getMonthValue() == receitaRequest.getData().getMonthValue()) {
            throw new ReceitaDuplicadaException();
        }
    }

    private Receita checarSeExistePorId(Long id) {
        Optional<Receita> receitaBuscada = receitaRepository.findById(id);
        if (receitaBuscada.isPresent()) {
            return receitaBuscada.get();
        }
        throw new ReceitaNaoEncontradaException();
    }
}
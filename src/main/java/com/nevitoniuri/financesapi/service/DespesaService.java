package com.nevitoniuri.financesapi.service;

import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.exception.DespesaDuplicadaException;
import com.nevitoniuri.financesapi.exception.DespesaNaoEncontradaException;
import com.nevitoniuri.financesapi.mapper.DespesaMapper;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.repository.DespesaRepository;
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

import static com.nevitoniuri.financesapi.mapper.DespesaMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private static final DespesaMapper despesaMapper = INSTANCE;
    private final DespesaRepository despesaRepository;

    public Page<DespesaDTO> listar(String descricao, Pageable pageable) {
        List<Despesa> despesas;
        if (descricao == null) {
            despesas = despesaRepository.findAll(pageable).getContent();
        } else {
            despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao, pageable).getContent();
        }
        List<DespesaDTO> collect = despesas.stream().map(despesaMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, despesas.size());
    }

    public DespesaDTO buscarPorId(Long id) throws DespesaNaoEncontradaException {
        Despesa despesaBuscada = checarSeExistePorId(id);
        return despesaMapper.toDTO(despesaBuscada);
    }

    public Page<DespesaDTO> buscarPeloAnoMes(Integer ano, Integer mes, Pageable pageable) {
        List<Despesa> despesasEncontradas = despesaRepository.findAll(pageable).getContent();
        List<Despesa> despesasDentroDoRangeDesejado = new ArrayList<>();
        for (Despesa despesaEncontrada : despesasEncontradas) {
            LocalDate despesa = despesaEncontrada.getData();
            if (despesa.getYear() == ano && despesa.getMonthValue() == mes) {
                despesasDentroDoRangeDesejado.add(despesaEncontrada);
            }
        }
        List<DespesaDTO> collect = despesasDentroDoRangeDesejado.stream().map(despesaMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, despesasDentroDoRangeDesejado.size());
    }

    @Transactional
    public DespesaDTO cadastrar(DespesaRequest despesaRequest) throws DespesaDuplicadaException {
        checarSeExisteNoMesmoMes(despesaRequest);
        Despesa despesaSalva = despesaRepository.save(despesaMapper.toEntity(despesaRequest));
        return despesaMapper.toDTO(despesaSalva);
    }

    @Transactional
    public DespesaDTO atualizar(Long id, DespesaRequest despesaRequest) throws DespesaNaoEncontradaException {
        Despesa despesaBuscada = checarSeExistePorId(id);
        despesaBuscada.setDescricao(despesaRequest.getDescricao());
        despesaBuscada.setValor(despesaRequest.getValor());
        despesaBuscada.setData(despesaRequest.getData());
        despesaBuscada.setCategoria(despesaRequest.getCategoria());
        return despesaMapper.toDTO(despesaRepository.saveAndFlush(despesaBuscada));
    }

    @Transactional
    public void deletar(Long id) throws DespesaNaoEncontradaException {
        checarSeExistePorId(id);
        despesaRepository.deleteById(id);
    }

    private Despesa checarSeExistePorId(Long id) {
        Optional<Despesa> despesaBuscada = despesaRepository.findById(id);
        if (despesaBuscada.isPresent()) {
            return despesaBuscada.get();
        }
        throw new DespesaNaoEncontradaException();
    }

    private void checarSeExisteNoMesmoMes(DespesaRequest despesaRequest) {
        Optional<Despesa> despesaBuscada = despesaRepository.findByDescricaoIgnoreCase(despesaRequest.getDescricao());
        if (despesaBuscada.isPresent() && despesaBuscada.get().getData().getMonth() == despesaRequest.getData().getMonth()) {
            throw new DespesaDuplicadaException();
        }
    }
}
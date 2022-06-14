package com.nevitoniuri.financesapi.service;

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

    public Despesa buscarPorId(Long id) {
        return despesaRepository.findById(id).orElseThrow(DespesaNaoEncontradaException::new);
    }

    public Page<Despesa> listar(String descricao, Pageable pageable) {
        Page<Despesa> despesas;
        if (descricao == null) {
            despesas = despesaRepository.findAll(pageable);
        } else {
            despesas = despesaRepository.findByDescricaoContainingIgnoreCase(descricao.trim(), pageable);
        }
        return despesas;
    }

    public Page<DespesaDTO> buscarPeloAnoMes(Integer ano, Integer mes, Pageable pageable) {
        List<Despesa> despesas = despesaRepository.findAll(pageable).getContent();
        List<Despesa> despesasAnoMes = new ArrayList<>();

        despesas.forEach(despesa -> {
            LocalDate despesaData = despesa.getData();
            if (despesaData.getYear() == ano && despesaData.getMonthValue() == mes) {
                despesasAnoMes.add(despesa);
            }
        });
        List<DespesaDTO> collect = despesasAnoMes.stream().map(despesaMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, despesasAnoMes.size());
    }

    @Transactional
    public Despesa salvar(Despesa despesa) {
        existeNoMesmoMesAno(despesa);
        return despesaRepository.save(despesa);
    }

    @Transactional
    public void deletar(Long id) throws DespesaNaoEncontradaException {
        despesaRepository.delete(buscarPorId(id));
    }

    private void existeNoMesmoMesAno(Despesa despesa) {
        Optional<List<Despesa>> despesas = despesaRepository.findByDescricaoIgnoreCase(despesa.getDescricao());
        despesas.ifPresent(despesasList -> despesasList.forEach(despesaList -> {
            LocalDate data = despesaList.getData();
            if (data.getYear() == despesa.getData().getYear() && data.getMonthValue() == despesa.getData().getMonthValue()) {
                throw new DespesaDuplicadaException();
            }
        }));
    }
}
package com.nevitoniuri.financesapi.service;

import com.nevitoniuri.financesapi.model.Categoria;
import com.nevitoniuri.financesapi.model.dto.CategoriaDTO;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.model.dto.ReceitaDTO;
import com.nevitoniuri.financesapi.model.dto.ResumoDoMesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumoService {

    private final DespesaService despesaService;
    private final ReceitaService receitaService;

    public ResumoDoMesDTO gerarResumoMensal(Integer ano, Integer mes, Pageable pageable) {
        Page<DespesaDTO> despesas = despesaService.buscarPeloAnoMes(ano, mes, pageable);
        Page<ReceitaDTO> receitas = receitaService.buscarPeloAnoMes(ano, mes, pageable);
        List<CategoriaDTO> totalGastoPorCategoria = new ArrayList<>();

        Map<Categoria, BigDecimal> gastoPorCategoria = despesas.stream().collect(Collectors.groupingBy(DespesaDTO::getCategoria,
                Collectors.mapping(DespesaDTO::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        gastoPorCategoria.forEach((categoria, valorTotal) -> totalGastoPorCategoria.add(CategoriaDTO.builder()
                .categoria(categoria).total(valorTotal).build()));

        BigDecimal totalDespesas = despesas.stream().map(DespesaDTO::getValor).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal totalReceitas = receitas.stream().map(ReceitaDTO::getValor).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal total = totalReceitas.subtract(totalDespesas);

        return ResumoDoMesDTO.builder()
                .totalDespesas(totalDespesas)
                .totalReceitas(totalReceitas)
                .saldoFinal(total)
                .gastosPorCategoria(totalGastoPorCategoria)
                .build();
    }
}
package com.nevitoniuri.financesapi.controller;

import com.nevitoniuri.financesapi.model.dto.ResumoDoMesDTO;
import com.nevitoniuri.financesapi.service.ResumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resumo")
@RequiredArgsConstructor
public class ResumoController {

    private final ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<ResumoDoMesDTO> gerarResumoMensal(@PathVariable Integer ano, @PathVariable Integer mes,
                                                            @PageableDefault(page = 0, size = 10, sort = "descricao") Pageable pageable) {
        return ResponseEntity.ok(resumoService.gerarResumoMensal(ano, mes, pageable));
    }
}

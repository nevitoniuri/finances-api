package com.nevitoniuri.financesapi.controller;

import com.nevitoniuri.financesapi.controller.request.ReceitaRequest;
import com.nevitoniuri.financesapi.model.dto.ReceitaDTO;
import com.nevitoniuri.financesapi.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("receitas")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<Page<ReceitaDTO>> listar(@RequestParam(required = false) String descricao,
                                                   @PageableDefault(page = 0, size = 10, sort = "descricao") Pageable pageable) {
        return ResponseEntity.ok(receitaService.listar(descricao, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ReceitaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
    }

    @GetMapping("{ano}/{mes}")
    public ResponseEntity<Page<ReceitaDTO>> buscarPeloAnoMes(@PathVariable Integer ano, @PathVariable Integer mes,
                                                             @PageableDefault(page = 0, size = 10, sort = "descricao") Pageable pageable) {
        return ResponseEntity.ok(receitaService.buscarPeloAnoMes(ano, mes, pageable));
    }

    @PostMapping
    public ResponseEntity<ReceitaDTO> cadastrar(@RequestBody @Valid ReceitaRequest receitaRequest,
                                                UriComponentsBuilder uriComponentsBuilder) {
        ReceitaDTO receitaSalva = receitaService.cadastrar(receitaRequest);
        URI uri = uriComponentsBuilder.path("/receitas/{id}").buildAndExpand(receitaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(receitaSalva);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReceitaDTO> atualizar(@PathVariable Long id,
                                                @RequestBody @Valid ReceitaRequest receitaRequest) {
        return ResponseEntity.ok(receitaService.atualizar(id, receitaRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

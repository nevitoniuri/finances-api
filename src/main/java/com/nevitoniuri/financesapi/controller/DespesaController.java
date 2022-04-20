package com.nevitoniuri.financesapi.controller;

import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<DespesaDTO>> listarDespesas(@RequestParam(required = false) String descricao){
        if (descricao == null) {
            return ResponseEntity.ok(despesaService.listarDespesas());
        }
        return ResponseEntity.ok(despesaService.listarDespesasPorDescricao(descricao));
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> salvarDespesa(@RequestBody @Valid DespesaDTO despesaDTO,
                                                    UriComponentsBuilder uriComponentsBuilder){
        DespesaDTO despesaSalva = despesaService.salvarDespesa(despesaDTO);
        URI uri = uriComponentsBuilder.path("/despesas/{id}").buildAndExpand(despesaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(despesaSalva);
    }

}

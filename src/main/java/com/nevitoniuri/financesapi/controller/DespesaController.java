package com.nevitoniuri.financesapi.controller;

import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<DespesaDTO>> listar(@RequestParam(required = false) String descricao) {
        return ResponseEntity.ok(despesaService.listar(descricao));
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(despesaService.buscarPorIdToDTO(id));
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> salvar(@RequestBody @Valid DespesaRequest despesaRequest,
                                             UriComponentsBuilder uriComponentsBuilder) {
        DespesaDTO despesaSalva = despesaService.salvar(despesaRequest);
        URI uri = uriComponentsBuilder.path("/despesas/{id}").buildAndExpand(despesaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(despesaSalva);
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaDTO> atualizar(@PathVariable Long id,
                                    @RequestBody @Valid DespesaRequest despesaRequest) {
        return ResponseEntity.ok(despesaService.atualizar(id, despesaRequest));
    }

}

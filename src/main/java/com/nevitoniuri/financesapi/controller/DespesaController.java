package com.nevitoniuri.financesapi.controller;

import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.service.DespesaService;
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
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @GetMapping
    public ResponseEntity<Page<DespesaDTO>> listar(@RequestParam(required = false) String descricao,
                                                   @PageableDefault(page = 0, size = 10, sort = "descricao") Pageable pageable) {
        return ResponseEntity.ok(despesaService.listar(descricao, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(despesaService.buscarPorId(id));
    }

    @GetMapping("{ano}/{mes}")
    public ResponseEntity<Page<DespesaDTO>> buscaPeloMesAno(@PathVariable Integer ano, @PathVariable Integer mes,
                                                            @PageableDefault(page = 0, size = 10, sort = "descricao") Pageable pageable) {
        return ResponseEntity.ok(despesaService.buscaPeloMesAno(ano, mes, pageable));
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> cadastrar(@RequestBody @Valid DespesaRequest despesaRequest,
                                                UriComponentsBuilder uriComponentsBuilder) {
        DespesaDTO despesaSalva = despesaService.cadastrar(despesaRequest);
        URI uri = uriComponentsBuilder.path("/despesas/{id}").buildAndExpand(despesaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(despesaSalva);
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaDTO> atualizar(@PathVariable Long id,
                                                @RequestBody @Valid DespesaRequest despesaRequest) {
        return ResponseEntity.ok(despesaService.atualizar(id, despesaRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        despesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}

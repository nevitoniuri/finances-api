package com.nevitoniuri.financesapi.controller;

import com.nevitoniuri.financesapi.controller.assembler.DespesaAssembler;
import com.nevitoniuri.financesapi.controller.disassembler.DespesaDisassembler;
import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.service.DespesaService;
import com.nevitoniuri.financesapi.util.RecursoCriado;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;
    private final DespesaAssembler despesaAssembler;
    private final DespesaDisassembler despesaDisassembler;

    @GetMapping("{id}")
    public DespesaDTO buscarPorId(@PathVariable Long id) {
        return despesaDisassembler.toDTO(despesaService.buscarPorId(id));
    }

    @GetMapping
    public Page<DespesaDTO> listar(@RequestParam(required = false) String descricao,
                                   @PageableDefault(sort = "descricao") Pageable pageable) {
        return despesaDisassembler.toDTOPage(pageable, despesaService.listar(descricao, pageable));
    }

    @GetMapping("{ano}/{mes}")
    public Page<DespesaDTO> buscarPeloAnoMes(@PathVariable Integer ano, @PathVariable Integer mes,
                                             @PageableDefault(sort = "descricao") Pageable pageable) {
        return despesaService.buscarPeloAnoMes(ano, mes, pageable);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid DespesaRequest despesaRequest) {
        var despesaSalva = despesaService.salvar(despesaAssembler.toEntity(despesaRequest));
        return ResponseEntity.created(RecursoCriado.location(despesaSalva.getId())).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id,
                          @RequestBody @Valid DespesaRequest despesaRequest) {
        Despesa despesa = despesaService.buscarPorId(id);
        despesaAssembler.copyToEntity(despesaRequest, despesa);
        despesaService.salvar(despesa);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        despesaService.deletar(id);
    }

}

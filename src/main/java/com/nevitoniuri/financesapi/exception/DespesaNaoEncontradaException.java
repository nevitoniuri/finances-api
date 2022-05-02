package com.nevitoniuri.financesapi.exception;

public class DespesaNaoEncontradaException extends BadRequestException {

    public DespesaNaoEncontradaException() {
        super("Despesa não encontrada");
    }
}

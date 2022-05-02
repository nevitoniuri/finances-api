package com.nevitoniuri.financesapi.exception;

public class ReceitaNaoEncontradaException extends BadRequestException {
    public ReceitaNaoEncontradaException() {
        super("Receita não encontrada.");
    }
}

package com.nevitoniuri.financesapi.exception;

public class DespesaDuplicadaException extends BadRequestException {

    public DespesaDuplicadaException() {
        super("Despesa já cadastrada. Por favor verifique a descrição informada.");
    }
}

package com.nevitoniuri.financesapi.exception;

public class ReceitaDuplicadaException extends BadRequestException {
    public ReceitaDuplicadaException() {
        super("Receita já cadastrada. Por favor verifique os dados informados.");
    }
}

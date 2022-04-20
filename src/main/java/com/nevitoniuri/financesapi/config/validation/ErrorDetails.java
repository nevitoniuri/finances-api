package com.nevitoniuri.financesapi.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    private String campo;
    private String mensagem;
}
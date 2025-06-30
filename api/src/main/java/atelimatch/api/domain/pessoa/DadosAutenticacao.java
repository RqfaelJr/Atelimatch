package atelimatch.api.domain.pessoa;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(

    @NotBlank
    String usuario,

    @NotBlank
    String senha

) {
}

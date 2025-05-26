package atelimatch.api.domain.rua;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroRua(

        @NotNull
        String nomeRua,
        Integer numeroRua,
        String complemento
) {
}

package atelimatch.api.domain.cidade;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroCIdade(

        @NotNull
        String nomeCidade
) {
}

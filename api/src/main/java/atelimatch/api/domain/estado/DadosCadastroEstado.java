package atelimatch.api.domain.estado;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroEstado(

        @NotNull
        String UF,

        @NotNull
        String nomeEstado
) {
}

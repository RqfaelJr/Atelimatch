package atelimatch.api.domain.atelie;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAtelie(
        @NotNull
        Integer idPessoa
) {
}

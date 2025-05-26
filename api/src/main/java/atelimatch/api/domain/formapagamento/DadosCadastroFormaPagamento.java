package atelimatch.api.domain.formapagamento;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroFormaPagamento(
        @NotNull
        String nomeFormaPagamento

) {
}

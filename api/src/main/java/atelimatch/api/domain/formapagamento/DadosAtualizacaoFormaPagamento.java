package atelimatch.api.domain.formapagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoFormaPagamento(
        @NotNull
        Integer idFormaPagamento,
        @NotBlank
        String nomeFormaPagamento
) {
}

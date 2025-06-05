package atelimatch.api.domain.formapagamento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroFormaPagamento(
        @NotBlank
        String nomeFormaPagamento

) {
}

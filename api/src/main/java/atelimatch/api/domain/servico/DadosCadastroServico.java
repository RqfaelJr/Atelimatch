package atelimatch.api.domain.servico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroServico(

        @NotBlank
        String nomeServico,
        @NotBlank
        Integer tempoMedio,
        @NotBlank
        Float valorServico

) {
}

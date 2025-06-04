package atelimatch.api.domain.servico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoServico(

        @NotNull
        Integer idServico,
        String nomeServico,

        Integer tempoMedio,

        Float valorServico
) {
}

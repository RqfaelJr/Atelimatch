package atelimatch.api.domain.materiaprima;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosAtualizacaoMateriaPrima(

        @NotNull
        Integer idMateriaPrima,
        @NotBlank
        String nomeMateriaPrima,
        @NotBlank
        Float qtdeMateriaPrima,
        @NotBlank
        String unidadeMateriaPrima
) {
}

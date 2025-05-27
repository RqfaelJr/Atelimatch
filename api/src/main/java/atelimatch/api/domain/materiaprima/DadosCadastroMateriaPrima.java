package atelimatch.api.domain.materiaprima;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMateriaPrima(

        @NotBlank
        String nomeMateriaPrima,
        @NotBlank
        Float qtdeMateriaPrima,
        @NotBlank
        String unidadeMateriaPrima


) {
}

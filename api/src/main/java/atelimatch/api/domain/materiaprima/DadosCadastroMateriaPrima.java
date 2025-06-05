package atelimatch.api.domain.materiaprima;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMateriaPrima(

        @NotBlank
        String nomeMateriaPrima,
        @NotNull
        Float qtdeMateriaPrima,
        @NotBlank
        String unidadeMateriaPrima


) {
}

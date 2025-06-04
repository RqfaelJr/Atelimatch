package atelimatch.api.domain.materiaprima;

import jakarta.validation.constraints.NotNull;

public record DadosMateriaPrima(

        @NotNull
        Integer idMateriaPrima
) {
}

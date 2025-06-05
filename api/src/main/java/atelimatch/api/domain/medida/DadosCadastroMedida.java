package atelimatch.api.domain.medida;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedida(

        @NotBlank
        String categoria,
        @NotNull
        Integer valorMedida
) {
}

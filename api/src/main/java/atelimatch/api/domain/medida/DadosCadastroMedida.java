package atelimatch.api.domain.medida;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMedida(

        @NotBlank
        String categoria,
        @NotBlank
        Integer valorMedida
) {
}

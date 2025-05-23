package atelimatch.api.domain.bairro;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroBairro(

        @NotBlank
        String nomeBairro
) {
}

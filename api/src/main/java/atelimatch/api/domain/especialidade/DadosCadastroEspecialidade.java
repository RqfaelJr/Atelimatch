package atelimatch.api.domain.especialidade;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEspecialidade(

        @NotBlank
        String descricaoEspecialidade
) {
}

package atelimatch.api.domain.atelie.especialidade;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEspecialidade(

        @NotBlank
        String descricaoEspecialidade
) {
}

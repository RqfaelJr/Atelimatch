package atelimatch.api.domain.pessoa.atelie.especialidade;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEspecialidade(

        @NotBlank
        String descricaoEspecialidade
) {
}

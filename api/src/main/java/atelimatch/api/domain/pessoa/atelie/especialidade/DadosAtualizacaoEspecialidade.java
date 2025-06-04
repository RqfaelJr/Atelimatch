package atelimatch.api.domain.pessoa.atelie.especialidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEspecialidade(

        @NotNull
        Integer idEspecialidade,
        @NotBlank
        String descricaoEspecialidade
) {
}

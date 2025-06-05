package atelimatch.api.domain.especialidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEspecialidade(

        @NotNull
        Integer idEspecialidade,
        @NotBlank
        String descricaoEspecialidade
) {
}

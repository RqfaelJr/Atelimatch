package atelimatch.api.domain.medida;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedida (
        @NotNull
        Integer idMedida,

        String categoria,
        Integer valorMedida
){
}

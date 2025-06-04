package atelimatch.api.domain.servico;


import atelimatch.api.domain.materiaprima.DadosMateriaPrima;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosServico(

        @NotNull
        Integer idServico,
        @NotBlank
        List<DadosMateriaPrima> materiasPrima

) {
}

package atelimatch.api.domain.servico;

import atelimatch.api.domain.materiaprima.DadosCadastroMateriaPrima;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroServico(

        @NotBlank
        String nomeServico,
        @NotNull
        Integer tempoMedio,
        @NotNull
        Float valorServico,
        @NotBlank
        List<DadosCadastroMateriaPrima> materiasPrima

) {
}

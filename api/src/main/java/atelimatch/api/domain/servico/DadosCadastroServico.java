package atelimatch.api.domain.servico;

import atelimatch.api.domain.materiaprima.DadosCadastroMateriaPrima;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DadosCadastroServico(

        @NotBlank
        String nomeServico,
        @NotBlank
        Integer tempoMedio,
        @NotBlank
        Float valorServico,
        @NotBlank
        List<DadosCadastroMateriaPrima> materiasPrima

) {
}

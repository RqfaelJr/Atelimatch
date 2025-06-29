package atelimatch.api.domain.servico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record DadosCadastroServico(

        @NotBlank
        String nomeServico,
        @NotNull
        Integer tempoMedio,
        @NotNull
        Float valorServico

) {
}

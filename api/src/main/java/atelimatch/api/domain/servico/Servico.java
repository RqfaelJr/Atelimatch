package atelimatch.api.domain.servico;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
    private Integer idServico;
    private String nomeServico;
    private Integer tempoMedio;
    private Float valorServico;

    public Servico(@Valid DadosCadastroServico dados) {
        this.nomeServico = dados.nomeServico();
        this.tempoMedio = dados.tempoMedio();
        this.valorServico = dados.valorServico();
    }
}

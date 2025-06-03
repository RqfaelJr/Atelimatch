package atelimatch.api.domain.servico;

import atelimatch.api.domain.pessoa.atelie.Atelie;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
    private Integer idServico;
    private String nomeServico;
    private Integer tempoMedio;
    private Float valorServico;

    @ManyToMany(mappedBy = "servicos")
    private Set<Atelie> atelies = new HashSet<>();


    public Servico(DadosCadastroServico dados) {
        this.nomeServico = dados.nomeServico();
        this.tempoMedio = dados.tempoMedio();
        this.valorServico = dados.valorServico();
    }
}

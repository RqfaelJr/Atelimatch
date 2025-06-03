package atelimatch.api.domain.materiaprima;

import atelimatch.api.domain.servico.Servico;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrima {
    private Integer idMateriaPrima;
    private String nomeMateriaPrima;
    private Float qtdeMateriaPrima;
    private String unidadeMateriaPrima;

    @OneToMany(mappedBy = "materiaprima")
    private Set<Servico> servicos = new HashSet<>();


    public MateriaPrima(DadosCadastroMateriaPrima  dados) {
        this.nomeMateriaPrima = dados.nomeMateriaPrima();
        this.qtdeMateriaPrima = dados.qtdeMateriaPrima();
        this.unidadeMateriaPrima = dados.unidadeMateriaPrima();
    }
}

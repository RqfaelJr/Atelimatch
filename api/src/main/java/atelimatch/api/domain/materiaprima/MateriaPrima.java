package atelimatch.api.domain.materiaprima;

import atelimatch.api.domain.servico.MateriaPrimaServico;
import atelimatch.api.domain.servico.Servico;
import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "materiaPrima", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MateriaPrimaServico> servicoMateriasPrima = new HashSet<>();


    public MateriaPrima(DadosCadastroMateriaPrima  dados) {
        this.nomeMateriaPrima = dados.nomeMateriaPrima();
        this.qtdeMateriaPrima = dados.qtdeMateriaPrima();
        this.unidadeMateriaPrima = dados.unidadeMateriaPrima();
    }

    public DadosDetalhamentoMateriaPrima atualizar(DadosAtualizacaoMateriaPrima dados) {
        if (dados.nomeMateriaPrima() != null) this.nomeMateriaPrima = dados.nomeMateriaPrima();
        if (dados.qtdeMateriaPrima() != null) this.qtdeMateriaPrima = dados.qtdeMateriaPrima();
        if (dados.unidadeMateriaPrima() != null) this.unidadeMateriaPrima = dados.unidadeMateriaPrima();

        return new DadosDetalhamentoMateriaPrima(this);
    }
}

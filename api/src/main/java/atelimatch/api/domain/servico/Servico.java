package atelimatch.api.domain.servico;

import atelimatch.api.domain.materiaprima.MateriaPrima;
import atelimatch.api.domain.pedido.Pedido;
import atelimatch.api.domain.pedido.pedidoservico.PedidoServico;
import atelimatch.api.domain.pessoa.atelie.Atelie;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MateriaPrimaServico> servicoMateriasPrima = new HashSet<>();

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoServico> servicoPedidos = new HashSet<>();



    public Servico(DadosCadastroServico dados) {
        this.nomeServico = dados.nomeServico();
        this.tempoMedio = dados.tempoMedio();
        this.valorServico = dados.valorServico();
    }

    public DadosDetalhamentoServico atualizar(DadosAtualizacaoServico dados){
        if (dados.nomeServico() != null) this.nomeServico = dados.nomeServico();
        if (dados.tempoMedio() != null) this.tempoMedio = dados.tempoMedio();
        if (dados.valorServico() != null) this.valorServico = dados.valorServico();

        return new DadosDetalhamentoServico(this);
    }
}

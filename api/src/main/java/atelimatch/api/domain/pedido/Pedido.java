package atelimatch.api.domain.pedido;

import atelimatch.api.domain.formapagamento.FormaPagamento;
import atelimatch.api.domain.medida.Medida;
import atelimatch.api.domain.pedido.pedidoservico.PedidoServico;
import atelimatch.api.domain.pessoa.atelie.Atelie;
import atelimatch.api.domain.pessoa.cliente.Cliente;
import atelimatch.api.domain.servico.Servico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Pedido")
@Entity(name = "Pedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAtelie")
    private Atelie atelie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    private Float valorTotal;
    private String descricaoPedido;
    private LocalDate dataEntrega;
    private LocalDate dataPrevisaoEntrega;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFormaPagamento")
    private FormaPagamento formaPagamento;
    @Column(name = "foto_peca")
    private byte[] foto;

    @ManyToMany
    @JoinTable(
            name = "medida_pedido",
            joinColumns = @JoinColumn(name = "idPedido"),
            inverseJoinColumns = @JoinColumn(name = "idMedida")
    )
    private Set<Medida> medidas = new HashSet<>();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoServico> pedidoServicos = new HashSet<>();

    public Pedido(Atelie atelie, Cliente cliente, Float valorTotal, String descricaoPedido, LocalDate dataPrevisaoEntrega, Status status, FormaPagamento formaPagamento, byte[] foto, Set<Medida> medidas) {
        this.atelie = atelie;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.descricaoPedido = descricaoPedido;
        this.dataPrevisaoEntrega = dataPrevisaoEntrega;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.foto = foto;
        this.medidas = medidas;
    }


    public void atualizar(DadosAtualizacaoPedido dados) {
        if (dados.dataEntrega() != null) this.dataEntrega = dados.dataEntrega();
        if (dados.status() != null) this.status = dados.status();
        if (dados.dataPrevisaoEntrega() != null) this.dataPrevisaoEntrega = dados.dataPrevisaoEntrega();

    }

    public void excluir() {
        this.status = Status.CANCELADO;
    }
}

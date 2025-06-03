package atelimatch.api.domain.pedido;

import atelimatch.api.domain.formapagamento.FormaPagamento;
import atelimatch.api.domain.medida.Medida;
import atelimatch.api.domain.pessoa.atelie.Atelie;
import atelimatch.api.domain.pessoa.cliente.Cliente;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer idPedido;
    private Atelie atelie;
    private Cliente cliente;
    private Float valorTotal;
    private String descricaoPedido;
    private LocalDate dataEntrega;
    private LocalDate dataPrevisaoEntrega;
    private Status status;
    private FormaPagamento formaPagamento;
    private byte[] foto;

    @ManyToMany
    @JoinTable(
            name = "PedidoMedida",
            joinColumns = @JoinColumn(name = "idPedido"),
            inverseJoinColumns = @JoinColumn(name = "idMedida")
    )
    private Set<Medida> medidas = new HashSet<>();

    public Pedido(Atelie atelie, Cliente cliente, Float valorTotal, LocalDate dataEntrega, LocalDate dataPrevisaoEntrega, Status status, FormaPagamento formaPagamento, byte[] foto, Set<Medida> medidas) {
        this.atelie = atelie;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.dataEntrega = dataEntrega;
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

package atelimatch.api.domain.pedido;

import atelimatch.api.domain.pessoa.atelie.Atelie;
import atelimatch.api.domain.pessoa.cliente.Cliente;
import atelimatch.api.domain.formapagamento.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer idPedido;
    private Atelie atelie;
    private Cliente cliente;
    private Float valorTotal;
    private LocalDate dataEntrega;
    private LocalDate dataPrevisaoEntrega;
    private Status status;
    private FormaPagamento formaPagamento;


    public void atualizar(DadosAtualizacaoPedido dados) {
        if (dados.dataEntrega() != null) this.dataEntrega = dados.dataEntrega();
        if (dados.status() != null) this.status = dados.status();
        if (dados.dataPrevisaoEntrega() != null) this.dataPrevisaoEntrega = dados.dataPrevisaoEntrega();

    }

    public void excluir() {
        this.status = Status.CANCELADO;
    }
}

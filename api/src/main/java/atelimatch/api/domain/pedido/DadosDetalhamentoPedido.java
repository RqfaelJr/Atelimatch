package atelimatch.api.domain.pedido;

import java.time.LocalDate;

public record DadosDetalhamentoPedido(
        Integer idPedido, Integer idAtelie, Integer idCliente, Float valorTotal,
        LocalDate dataEntrega, LocalDate dataPrevisaoEntrega, Status status, Integer idFormaPagamento) {
    public DadosDetalhamentoPedido(Pedido pedido) {
        this(pedido.getIdPedido(), pedido.getAtelie().getIdPessoa(), pedido.getCliente().getIdPessoa(), pedido.getValorTotal(),
                pedido.getDataEntrega(), pedido.getDataPrevisaoEntrega(), pedido.getStatus(), pedido.getFormaPagamento().getIdFormaPagamento());
    }
}

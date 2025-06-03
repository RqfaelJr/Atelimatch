package atelimatch.api.domain.pedido;

import atelimatch.api.domain.medida.Medida;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoPedido(
        Integer idPedido, Integer idAtelie, Integer idCliente, Float valorTotal, String descricaoPedido,
        LocalDate dataEntrega, LocalDate dataPrevisaoEntrega, Status status, Integer idFormaPagamento, byte[] foto, List<Integer> idsMedida) {
    public DadosDetalhamentoPedido(Pedido pedido) {
        this(pedido.getIdPedido(), pedido.getAtelie().getIdPessoa(), pedido.getCliente().getIdPessoa(), pedido.getValorTotal(), pedido.getDescricaoPedido(),
                pedido.getDataEntrega(), pedido.getDataPrevisaoEntrega(), pedido.getStatus(), pedido.getFormaPagamento().getIdFormaPagamento(), pedido.getFoto(), pedido.getMedidas().stream().map(Medida::getIdMedida)
                        .collect(Collectors.toList()));
    }
}

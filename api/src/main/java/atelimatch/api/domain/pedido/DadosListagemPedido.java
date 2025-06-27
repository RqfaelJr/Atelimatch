package atelimatch.api.domain.pedido;

public record DadosListagemPedido(String descricao) {
    public DadosListagemPedido(Pedido pedido) {
        this(pedido.getDescricaoPedido());
    }

     // ARRUMAR DPS
}

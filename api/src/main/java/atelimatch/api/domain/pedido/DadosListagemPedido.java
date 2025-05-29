package atelimatch.api.domain.pedido;

public record DadosListagemPedido(String nomeCliente, String nomeAtelie) {
    public DadosListagemPedido(Pedido pedido) {
        this(pedido.getCliente().getNomePessoa(), pedido.getAtelie().getNomePessoa());
    }

     // ARRUMAR DPS
}

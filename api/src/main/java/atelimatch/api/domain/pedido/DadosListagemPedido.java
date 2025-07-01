package atelimatch.api.domain.pedido;

public record DadosListagemPedido(Integer idPedido, String descricao, String nomeAtelie, Status status) {
    public DadosListagemPedido(Pedido pedido) {
        this(pedido.getIdPedido(), pedido.getDescricaoPedido(), pedido.getAtelie().getNomePessoa(), pedido.getStatus());
    }


}

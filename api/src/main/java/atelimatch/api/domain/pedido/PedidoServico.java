package atelimatch.api.domain.pedido;

import atelimatch.api.domain.servico.Servico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoServico {
    private Pedido pedido;
    private Servico servico;
    private Float valorServicoPedido;
}

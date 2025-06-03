package atelimatch.api.domain.pedido.pedidoservico;

import atelimatch.api.domain.pedido.Pedido;
import atelimatch.api.domain.servico.Servico;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoServico {

    @EmbeddedId
    private PedidoServicoId idPedidoServico;

    @ManyToOne
    @MapsId("idPedido")
    private Pedido pedido;
    @ManyToOne
    @MapsId("idServico")
    private Servico servico;
    private Float valorServicoPedido;
}

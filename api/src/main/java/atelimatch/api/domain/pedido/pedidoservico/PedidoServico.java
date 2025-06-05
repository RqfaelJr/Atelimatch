package atelimatch.api.domain.pedido.pedidoservico;

import atelimatch.api.domain.pedido.Pedido;
import atelimatch.api.domain.servico.Servico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "PedidoServico")
@Entity(name = "PedidoServico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPedidoServico")
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

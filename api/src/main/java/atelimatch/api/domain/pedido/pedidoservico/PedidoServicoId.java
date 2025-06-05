package atelimatch.api.domain.pedido.pedidoservico;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoServicoId implements Serializable {
    private Integer idServico;
    private Integer idPedido;
}

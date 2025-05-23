package atelimatch.api.domain.pedido;

import atelimatch.api.domain.medida.Medida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoMedida {
    private Pedido pedido;
    private Medida Medida;
}

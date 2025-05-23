package atelimatch.api.domain.pedido;

import atelimatch.api.domain.atelie.Atelie;
import atelimatch.api.domain.cliente.Cliente;
import atelimatch.api.domain.formapagamento.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer idPedido;
    private Atelie atelie;
    private Cliente cliente;
    private Float valorTotal;
    private LocalDateTime dataEntrega;
    private LocalDateTime dataPrevisaoEntrega;
    private String status;
    private FormaPagamento formaPagamento;
}

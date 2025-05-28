package atelimatch.api.domain.pedido;

import atelimatch.api.domain.atelie.Atelie;
import atelimatch.api.domain.cliente.Cliente;
import atelimatch.api.domain.formapagamento.FormaPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroPedido(

        @NotNull
        Integer idAtelie,
        @NotNull
        Integer idCliente,
        @NotBlank
        Float valorTotal,
        @NotBlank
        LocalDate dataEntrega,
        @NotBlank
        LocalDate dataPrevisaoEntrega,
        @NotBlank
        Status status,
        @NotNull
        Integer idFormaPagamento
) {
}

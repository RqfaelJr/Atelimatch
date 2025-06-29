package atelimatch.api.domain.pedido;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoPedido(

        @NotNull
        Integer idPedido,
        @Future
        LocalDate dataEntrega,
        @Future
        LocalDate dataPrevisaoEntrega,
        Status status

) {
}

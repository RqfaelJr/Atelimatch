package atelimatch.api.domain.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoPedido(

        @NotNull
        Integer idPedido,
        @NotBlank
        LocalDate dataEntrega,
        @NotBlank
        LocalDate dataPrevisaoEntrega,
        @NotBlank
        Status status

) {
}

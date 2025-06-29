package atelimatch.api.domain.pedido;


import atelimatch.api.domain.servico.DadosServico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DadosCadastroPedido(

        @NotNull
        Integer idAtelie,
        @NotNull
        Integer idCliente,
        @NotBlank
        String descricaoPedido,
        Status status,
        @NotNull
        List<Integer> idsMedida,
        List<DadosServico> servicos,
        byte[] foto,
        @Future
        LocalDate dataPrevisaoEntrega,
        @NotNull
        Float valorTotal,
        @NotNull
        Integer idFormaPagamento
) {
}

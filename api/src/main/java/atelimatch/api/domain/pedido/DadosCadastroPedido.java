package atelimatch.api.domain.pedido;

import atelimatch.api.domain.servico.DadosCadastroServico;
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
        @NotNull
        Float valorTotal,
        @NotBlank
        String descricaoPedido,
        @Future
        LocalDate dataPrevisaoEntrega,
        Status status,
        @NotNull
        Integer idFormaPagamento,
        byte[] foto,
        @NotNull
        List<Integer> idsMedida,
        List<DadosServico> servicos
) {
}

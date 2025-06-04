package atelimatch.api.domain.pedido;

import atelimatch.api.domain.servico.DadosCadastroServico;
import atelimatch.api.domain.servico.DadosServico;
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
        Float valorTotal,
        @NotBlank
        String descricaoPedido,
        @NotBlank
        LocalDate dataEntrega,
        @NotBlank
        LocalDate dataPrevisaoEntrega,
        @NotBlank
        Status status,
        @NotNull
        Integer idFormaPagamento,
        byte[] foto,
        @NotNull
        List<Integer> idsMedida,
        @NotNull
        List<DadosServico> servicos
) {
}

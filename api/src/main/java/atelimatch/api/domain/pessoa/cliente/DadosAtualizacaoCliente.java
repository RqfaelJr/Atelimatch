package atelimatch.api.domain.pessoa.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull
        Integer idPessoa,
        String nomePessoa,

        String senha,

        String usuario,

        String telefone,

        Integer idEndereco


) {
}

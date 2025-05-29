package atelimatch.api.domain.pessoa.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull
        Integer idPessoa,
        String nomePessoa,

        @Email
        String email,

        String senha,

        String usuario,

        String telefone,

        Integer idEstado,

        Integer idCidade,

        Integer idBairro,

        Integer idRua


) {
}

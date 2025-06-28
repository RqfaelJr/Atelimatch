package atelimatch.api.domain.pessoa.cliente;

import java.time.LocalDate;

public record DadosDetalhamentoCliente(Integer idPessoa, String nomePessoa,
        String email,
        String senha,
        String usuario,
        String telefone, String cpf, LocalDate data, Integer idEndereco) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getIdPessoa(), cliente.getNomePessoa(), cliente.getEmail(), cliente.getSenha(), cliente.getUsuario(), cliente.getTelefone(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getEndereco().getIdEndereco());
    }
}

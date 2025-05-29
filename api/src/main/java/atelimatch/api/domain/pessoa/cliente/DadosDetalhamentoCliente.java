package atelimatch.api.domain.pessoa.cliente;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.rua.Rua;

import java.time.LocalDate;

public record DadosDetalhamentoCliente(Integer idPessoa, String nomePessoa,
        String email,
        String senha,
        String usuario,
        String telefone,
        Estado estado,
        Cidade cidade,
        Bairro bairro,
        Rua rua, String cpf, LocalDate data) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getIdPessoa(), cliente.getNomePessoa(), cliente.getEmail(), cliente.getSenha(), cliente.getUsuario(), cliente.getTelefone(), cliente.getEstado(), cliente.getCidade(), cliente.getBairro(), cliente.getRua(), cliente.getCpf(), cliente.getDataNascimento());
    }
}

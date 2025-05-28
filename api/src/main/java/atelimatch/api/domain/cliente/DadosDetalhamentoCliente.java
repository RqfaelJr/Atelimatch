package atelimatch.api.domain.cliente;

import java.time.LocalDate;

public record DadosDetalhamentoCliente(Integer idCliente, String cpf, LocalDate data, Integer idPessoa) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getIdCliente(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getPessoa().getIdPessoa());
    }
}

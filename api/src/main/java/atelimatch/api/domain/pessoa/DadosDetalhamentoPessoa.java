package atelimatch.api.domain.pessoa;

public record DadosDetalhamentoPessoa(Integer id, String nome, String email, String telefone) {
    public DadosDetalhamentoPessoa(Pessoa pessoa) {
        this(pessoa.getIdPessoa(), pessoa.getNomePessoa(), pessoa.getEmail(), pessoa.getTelefone());
    }
}


// ALTERAR ESSES DADOS DEPOIS

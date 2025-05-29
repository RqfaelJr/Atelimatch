package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.rua.Rua;

public record DadosDetalhamentoAtelie(Integer idPessoa, String nomePessoa,
                                      String email,
                                      String senha,
                                      String usuario,
                                      String telefone,
                                      Estado estado,
                                      Cidade cidade,
                                      Bairro bairro,
                                      Rua rua) {
    public DadosDetalhamentoAtelie(Atelie atelie) {
        this(atelie.getIdPessoa(), atelie.getNomePessoa(), atelie.getEmail(), atelie.getSenha(), atelie.getUsuario(), atelie.getTelefone(), atelie.getEstado(), atelie.getCidade(), atelie.getBairro(), atelie.getRua());
    }
}

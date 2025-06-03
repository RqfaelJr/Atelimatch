package atelimatch.api.domain.pessoa.atelie;


import atelimatch.api.domain.pessoa.atelie.especialidade.Especialidade;
import atelimatch.api.domain.rua.Rua;
import atelimatch.api.domain.servico.Servico;

import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoAtelie(Integer idPessoa, String nomePessoa,
                                      String email,
                                      String senha,
                                      String usuario,
                                      String telefone,
                                      String estado,
                                      String cidade,
                                      String bairro,
                                      String nomeRua,
                                      Integer numeroRua,
                                      Float notaAtelie,
                                      Integer qntdNotas,
                                      String especialidade,
                                      List<String> servicos
                                      ) {
    public DadosDetalhamentoAtelie(Atelie atelie) {
        this(atelie.getIdPessoa(), atelie.getNomePessoa(), atelie.getEmail(), atelie.getSenha(), atelie.getUsuario(), atelie.getTelefone(), atelie.getEstado().getNomeEstado(), atelie.getCidade().getNomeCidade(), atelie.getBairro().getNomeBairro(), atelie.getRua().getNomeRua(), atelie.getRua().getNumeroRua(), atelie.getNotaAvaliacao(), atelie.getQntdNotas(), atelie.getEspecialidade().getDescricaoEspecialidade(), atelie.getServicos().stream().map(Servico::getNomeServico).collect(Collectors.toList()));
    }
}

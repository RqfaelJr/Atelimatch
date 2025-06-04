package atelimatch.api.domain.pessoa.atelie;


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
                                      Float notaAtelie,
                                      Integer qntdNotas,
                                      String especialidade,
                                      List<String> servicos,
                                      Integer inicio01,
                                      Integer fim01,
                                      Integer inicio02,
                                      Integer fim02
                                      ) {
    public DadosDetalhamentoAtelie(Atelie atelie) {
        this(atelie.getIdPessoa(), atelie.getNomePessoa(), atelie.getEmail(), atelie.getSenha(), atelie.getUsuario(), atelie.getTelefone(), atelie.getEndereco().getEstado(), atelie.getEndereco().getCidade(), atelie.getEndereco().getBairro(), atelie.getEndereco().getRua(), atelie.getNotaAvaliacao(), atelie.getQntdNotas(), atelie.getEspecialidade().getDescricaoEspecialidade(), atelie.getServicos().stream().map(Servico::getNomeServico).collect(Collectors.toList()), atelie.getInicio01(), atelie.getFim01(), atelie.getInicio02(), atelie.getFim02());
    }
}

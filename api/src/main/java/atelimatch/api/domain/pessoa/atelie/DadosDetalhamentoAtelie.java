package atelimatch.api.domain.pessoa.atelie;


import atelimatch.api.domain.servico.Servico;

import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoAtelie(Integer idPessoa, String nomePessoa,
                                      String email,
                                      String senha,
                                      String usuario,
                                      String telefone,
                                      Float notaAtelie,
                                      Integer qntdNotas,
                                      String especialidade,
                                      List<String> servicos,
                                      Integer inicio01,
                                      Integer fim01,
                                      String cnpj,
                                      Integer idEndereco
                                      ) {
    public DadosDetalhamentoAtelie(Atelie atelie) {
        this(atelie.getIdPessoa(), atelie.getNomePessoa(), atelie.getEmail(), atelie.getSenha(), atelie.getUsuario(), atelie.getTelefone(), atelie.getNotaAvaliacao(), atelie.getQntdNotas(), atelie.getEspecialidade().getDescricaoEspecialidade(), atelie.getServicos().stream().map(Servico::getNomeServico).collect(Collectors.toList()), atelie.getInicio01(), atelie.getFim01(), atelie.getCnpj(), atelie.getEndereco().getIdEndereco());
    }
}

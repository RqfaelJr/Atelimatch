package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.endereco.EnderecoRepository;
import atelimatch.api.domain.especialidade.EspecialidadeRepository;
import atelimatch.api.domain.servico.Servico;
import atelimatch.api.domain.servico.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class CadastroAtelie {

    @Autowired
    private AtelieRepository atelieRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public DadosDetalhamentoAtelie cadastrar(DadosCadastroAtelie dados) {
        var endereco = enderecoRepository.getReferenceById(dados.idEndereco());
        var especialidade = especialidadeRepository.getReferenceById(dados.idEspecialidade());
        Set<Servico> servicos = new HashSet<>();
        for (int i = 0; i < dados.idsServico().toArray().length; i++) {
            servicos.add(servicoRepository.getReferenceById(dados.idsServico().get(i)));
        }
        var atelie = new Atelie(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), endereco, dados.cnpj(), especialidade, dados.inicio01(), dados.fim01(), dados.inicio02(), dados.fim02());
        atelie.getServicos().addAll(servicos);
        atelie = atelieRepository.saveAndFlush(atelie);


        return new DadosDetalhamentoAtelie(atelie);
    }

    public DadosDetalhamentoAtelie atualizar(DadosAtualizacaoAtelie dados) {
        var atelie = atelieRepository.getReferenceById(dados.idPessoa());
        if (dados.idEndereco() != null) {
            var endereco = enderecoRepository.getReferenceById(dados.idEndereco());
            atelie.atualizar(dados, endereco);
        } else {
            atelie.atualizar(dados, null);
        }


        atelieRepository.save(atelie);
        return new DadosDetalhamentoAtelie(atelie);
    }
}

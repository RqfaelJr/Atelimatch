package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.bairro.BairroRepository;
import atelimatch.api.domain.cidade.CidadeRepository;
import atelimatch.api.domain.estado.EstadoRepository;
import atelimatch.api.domain.pessoa.atelie.especialidade.EspecialidadeRepository;
import atelimatch.api.domain.rua.RuaRepository;
import atelimatch.api.domain.servico.Servico;
import atelimatch.api.domain.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CadastroAtelie {

    @Autowired
    private AtelieRepository atelieRepository;

    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private RuaRepository ruaRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public DadosDetalhamentoAtelie cadastrar(DadosCadastroAtelie dados){
        var estado = estadoRepository.getReferenceById(dados.idEstado());
        var cidade = cidadeRepository.getReferenceById(dados.idCidade());
        var bairro = bairroRepository.getReferenceById(dados.idBairro());
        var rua = ruaRepository.getReferenceById(dados.idRua());
        var especialidade = especialidadeRepository.getReferenceById(dados.idEspecialidade());
        Set<Servico> servicos = new HashSet<>();
        for (int i = 0; i < dados.idsServico().toArray().length; i++) {
            servicos.add(servicoRepository.getReferenceById(dados.idsServico().get(i)));
        }

        var atelie = new Atelie(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), estado, cidade, bairro, rua, especialidade, servicos);

        atelieRepository.save(atelie);
        return new DadosDetalhamentoAtelie(atelie);
    }

    public DadosDetalhamentoAtelie atualizar(DadosAtualizacaoAtelie dados){
        var atelie = atelieRepository.getReferenceById(dados.idPessoa());
        var bairro = bairroRepository.getReferenceById(dados.idBairro());
        var rua = ruaRepository.getReferenceById(dados.idRua());
        atelie.atualizar(dados, bairro, rua);


        atelieRepository.save(atelie);
        return new DadosDetalhamentoAtelie(atelie);
    }
}

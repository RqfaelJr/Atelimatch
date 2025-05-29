package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.bairro.BairroRepository;
import atelimatch.api.domain.cidade.CidadeRepository;
import atelimatch.api.domain.estado.EstadoRepository;
import atelimatch.api.domain.rua.RuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public DadosDetalhamentoAtelie cadastrar(DadosCadastroAtelie dados){
        var estado = estadoRepository.getReferenceById(dados.idEstado());
        var cidade = cidadeRepository.getReferenceById(dados.idCidade());
        var bairro = bairroRepository.getReferenceById(dados.idBairro());
        var rua = ruaRepository.getReferenceById(dados.idRua());

        var atelie = new Atelie(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), estado, cidade, bairro, rua);

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

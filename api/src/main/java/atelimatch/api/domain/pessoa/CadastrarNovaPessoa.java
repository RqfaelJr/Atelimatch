package atelimatch.api.domain.pessoa;

import atelimatch.api.domain.bairro.BairroRepository;
import atelimatch.api.domain.cidade.CidadeRepository;
import atelimatch.api.domain.estado.EstadoRepository;
import atelimatch.api.domain.rua.RuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarNovaPessoa {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private RuaRepository ruaRepository;

    public DadosDetalhamentoPessoa cadastro(DadosCadastroPessoa dados){
        var estado = estadoRepository.getReferenceById(dados.idEstado());
        var cidade = cidadeRepository.getReferenceById(dados.idCidade());
        var bairro = bairroRepository.getReferenceById(dados.idBairro());
        var rua = ruaRepository.getReferenceById(dados.idRua());
        var pessoa = new Pessoa(null, dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.usuario(), estado, cidade, bairro, rua);

        pessoaRepository.save(pessoa);
        return new DadosDetalhamentoPessoa(pessoa);
    }
}

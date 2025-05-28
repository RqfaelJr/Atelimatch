package atelimatch.api.domain.atelie;

import atelimatch.api.domain.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroAtelie {

    @Autowired
    private AtelieRepository atelieRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public DadosDetalhamentoAtelie cadastrar(DadosCadastroAtelie dados){
        var pessoa = pessoaRepository.getReferenceById(dados.idPessoa());
        var atelie = new Atelie(null, pessoa);

        atelieRepository.save(atelie);
        return new DadosDetalhamentoAtelie(atelie);
    }
}

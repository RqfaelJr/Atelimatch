package atelimatch.api.domain.cliente;

import atelimatch.api.domain.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroDeCliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public DadosDetalhamentoCliente cadastrar(DadosCadastroCliente dados){

        var pessoa = pessoaRepository.getReferenceById(dados.idPessoa());
        var cliente = new Cliente(null, dados.cpf(), dados.dataNascimento(), pessoa);

        clienteRepository.save(cliente);
    }
}

package atelimatch.api.domain.pessoa.cliente;


import atelimatch.api.domain.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroDeCliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public DadosDetalhamentoCliente cadastrar(DadosCadastroCliente dados){

        var endereco = enderecoRepository.getReferenceById(dados.idEndereco());


        var cliente = new Cliente(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), endereco, dados.cpf(), dados.dataNascimento());
        clienteRepository.save(cliente);

        return new DadosDetalhamentoCliente(cliente);
    }

    public DadosDetalhamentoCliente atualizar(DadosAtualizacaoCliente dados){
        var cliente = clienteRepository.getReferenceById(dados.idPessoa());
        if (dados.idEndereco() != null) {
            var endereco = enderecoRepository.getReferenceById(dados.idEndereco());
            cliente.atualizar(dados, endereco);
        } else {
            cliente.atualizar(dados, null);
        }

        clienteRepository.save(cliente);

        return new DadosDetalhamentoCliente(cliente);
    }
}

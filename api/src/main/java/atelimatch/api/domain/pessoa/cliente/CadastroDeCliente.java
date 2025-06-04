package atelimatch.api.domain.pessoa.cliente;

import atelimatch.api.domain.bairro.BairroRepository;
import atelimatch.api.domain.cidade.CidadeRepository;
import atelimatch.api.domain.endereco.DadosAtualizacaoEndereco;
import atelimatch.api.domain.endereco.EnderecoRepository;
import atelimatch.api.domain.estado.EstadoRepository;
import atelimatch.api.domain.rua.RuaRepository;
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
        var endereco = enderecoRepository.getReferenceById(dados.idEndereco());
        cliente.atualizar(dados, endereco);

        clienteRepository.save(cliente);

        return new DadosDetalhamentoCliente(cliente);
    }
}

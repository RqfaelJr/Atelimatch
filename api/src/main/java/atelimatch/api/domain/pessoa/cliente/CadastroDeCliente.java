package atelimatch.api.domain.pessoa.cliente;

import atelimatch.api.domain.bairro.BairroRepository;
import atelimatch.api.domain.cidade.CidadeRepository;
import atelimatch.api.domain.estado.EstadoRepository;
import atelimatch.api.domain.rua.RuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroDeCliente {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private RuaRepository ruaRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    public DadosDetalhamentoCliente cadastrar(DadosCadastroCliente dados){
        var estado = estadoRepository.getReferenceById(dados.idEstado());
        var cidade = cidadeRepository.getReferenceById(dados.idCidade());
        var bairro = bairroRepository.getReferenceById(dados.idBairro());
        var rua = ruaRepository.getReferenceById(dados.idRua());


        var cliente = new Cliente(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), estado, cidade, bairro, rua, dados.cpf(), dados.dataNascimento());
        clienteRepository.save(cliente);

        return new DadosDetalhamentoCliente(cliente);
    }
}

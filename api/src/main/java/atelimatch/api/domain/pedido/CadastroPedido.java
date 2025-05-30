package atelimatch.api.domain.pedido;

import atelimatch.api.domain.formapagamento.FormaPagamentoRepository;
import atelimatch.api.domain.pessoa.atelie.AtelieRepository;
import atelimatch.api.domain.pessoa.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPedido {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private AtelieRepository atelieRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public DadosDetalhamentoPedido cadastrar(DadosCadastroPedido dados){
        var atelie = atelieRepository.getReferenceById(dados.idAtelie());
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var formaPagamento = formaPagamentoRepository.getReferenceById(dados.idFormaPagamento());
        var pedido = new Pedido(null, atelie, cliente, dados.valorTotal(), dados.dataEntrega(), dados.dataPrevisaoEntrega(), dados.status(), formaPagamento, dados.foto());

        pedidoRepository.save(pedido);
        return new DadosDetalhamentoPedido(pedido);
    }

    public DadosDetalhamentoPedido atualizar(DadosAtualizacaoPedido dados){
        var pedido = pedidoRepository.getReferenceById(dados.idPedido());
        pedido.atualizar(dados);

        pedidoRepository.save(pedido);
        return new DadosDetalhamentoPedido(pedido);
    }
}

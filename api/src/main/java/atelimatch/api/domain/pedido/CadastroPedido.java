package atelimatch.api.domain.pedido;

import atelimatch.api.domain.formapagamento.FormaPagamentoRepository;
import atelimatch.api.domain.medida.Medida;
import atelimatch.api.domain.medida.MedidaRepository;
import atelimatch.api.domain.pedido.pedidoservico.PedidoServico;
import atelimatch.api.domain.pedido.pedidoservico.PedidoServicoId;
import atelimatch.api.domain.pedido.pedidoservico.PedidoServicoRepository;
import atelimatch.api.domain.pessoa.atelie.AtelieRepository;
import atelimatch.api.domain.pessoa.cliente.ClienteRepository;
import atelimatch.api.domain.servico.Servico;
import atelimatch.api.domain.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private PedidoServicoRepository pedidoServicoRepository;

    public DadosDetalhamentoPedido cadastrar(DadosCadastroPedido dados){
        var atelie = atelieRepository.getReferenceById(dados.idAtelie());
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var formaPagamento = formaPagamentoRepository.getReferenceById(dados.idFormaPagamento());

        Set<Medida> medidas = new HashSet<>();
        for (int i = 0; i < dados.idsMedida().toArray().length; i++) {
            medidas.add(medidaRepository.getReferenceById(dados.idsMedida().get(i)));
        }

        var pedido = new Pedido(atelie, cliente, dados.valorTotal(), dados.dataEntrega(), dados.dataPrevisaoEntrega(), dados.status(), formaPagamento, dados.foto(), medidas);

        var pedidoSalvo = pedidoRepository.save(pedido);

        List<Servico> servicos = servicoRepository.findAllById(dados.idsServico());

        for (Servico servico : servicos) {
            PedidoServico ps = new PedidoServico(new PedidoServicoId(pedidoSalvo.getIdPedido(), servico.getIdServico()), pedido, servico, servico.getValorServico());
            pedidoServicoRepository.save(ps);
        }

        return new DadosDetalhamentoPedido(pedidoSalvo);
    }

    public DadosDetalhamentoPedido atualizar(DadosAtualizacaoPedido dados){
        var pedido = pedidoRepository.getReferenceById(dados.idPedido());
        pedido.atualizar(dados);

        pedidoRepository.save(pedido);
        return new DadosDetalhamentoPedido(pedido);
    }
}

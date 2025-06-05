package atelimatch.api.domain.pedido;

import atelimatch.api.domain.formapagamento.FormaPagamentoRepository;
import atelimatch.api.domain.materiaprima.DadosMateriaPrima;
import atelimatch.api.domain.materiaprima.MateriaPrima;
import atelimatch.api.domain.materiaprima.MateriaPrimaRepository;
import atelimatch.api.domain.medida.Medida;
import atelimatch.api.domain.medida.MedidaRepository;
import atelimatch.api.domain.pedido.pedidoservico.PedidoServico;
import atelimatch.api.domain.pedido.pedidoservico.PedidoServicoId;
import atelimatch.api.domain.pessoa.atelie.AtelieRepository;
import atelimatch.api.domain.pessoa.cliente.ClienteRepository;
import atelimatch.api.domain.servico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    private MateriaPrimaRepository materiaPrimaRepository;

    public DadosDetalhamentoPedido cadastrar(DadosCadastroPedido dados){
        var atelie = atelieRepository.getReferenceById(dados.idAtelie());
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var formaPagamento = formaPagamentoRepository.getReferenceById(dados.idFormaPagamento());

        Set<Medida> medidas = new HashSet<>();
        for (int i = 0; i < dados.idsMedida().toArray().length; i++) {
            medidas.add(medidaRepository.getReferenceById(dados.idsMedida().get(i)));
        }

        var pedido = new Pedido(atelie, cliente, dados.valorTotal(), dados.descricaoPedido(), dados.dataPrevisaoEntrega(), dados.status(), formaPagamento, dados.foto(), medidas);

        Set<PedidoServico> pedidoServicos = new HashSet<>();

        for (DadosServico s : dados.servicos()) {
            Servico servico = servicoRepository.getReferenceById(s.idServico());

            PedidoServico ps = new PedidoServico(new PedidoServicoId(pedido.getIdPedido(), servico.getIdServico()), pedido, servico, servico.getValorServico());
            pedidoServicos.add(ps);

            for (DadosMateriaPrima Dmp : s.materiasPrima()) {
                MateriaPrima mp = materiaPrimaRepository.getReferenceById(Dmp.idMateriaPrima());

                MateriaPrimaServico mps = new MateriaPrimaServico(new MateriaPrimaServicoId(servico.getIdServico(), mp.getIdMateriaPrima()), servico, mp, mp.getQtdeMateriaPrima(), mp.getUnidadeMateriaPrima());
                servico.getServicoMateriasPrima().add(mps);
            }
        }
        pedido.getPedidoServicos().addAll(pedidoServicos);

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

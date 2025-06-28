package atelimatch.api.domain.pedido;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT new atelimatch.api.domain.pedido.DadosListagemPedido(p.idPedido, p.descricaoPedido, p.atelie.nomePessoa, p.status) " +
           "FROM Pedido p WHERE p.cliente.idPessoa = :id")
    Page<DadosListagemPedido> selecionarPedidoPorCliente(Integer id, Pageable pageable);

    @Query("SELECT new atelimatch.api.domain.pedido.DadosListagemPedido(p.idPedido, p.descricaoPedido, p.atelie.nomePessoa, p.status) " +
            "FROM Pedido p WHERE p.atelie.idPessoa = :id")
    Page<DadosListagemPedido> selecionarPedidoPorAtelie(Integer id, Pageable pageable);

}

package atelimatch.api.domain.pedido;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT new atelimatch.api.domain.pedido.DadosListagemPedido(p.descricaoPedido) " +
           "FROM Pedido p WHERE p.cliente.idPessoa = :id")
    Page<DadosListagemPedido> selecionarPedidoPorCliente(Integer id, Pageable pageable);

}

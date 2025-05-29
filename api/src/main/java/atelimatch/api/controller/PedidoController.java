package atelimatch.api.controller;

import atelimatch.api.domain.pedido.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private CadastroPedido cadastro;

    @Autowired
    private PedidoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPedido> cadastrar(@RequestBody @Valid DadosCadastroPedido dados, UriComponentsBuilder uriBuilder){
        var dto = cadastro.cadastrar(dados);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.idPedido()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPedido> atualizar(@RequestBody @Valid DadosAtualizacaoPedido dados) {
        var dto = cadastro.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        var pedido = repository.getReferenceById(id);
        pedido.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPedido> detalhar(@PathVariable Integer id) {
        var pedido = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPedido>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemPedido::new);
        return ResponseEntity.ok(page);
    }
}

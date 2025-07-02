package atelimatch.api.controller;

import atelimatch.api.domain.formapagamento.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/formapagamento")
@CrossOrigin(origins = "*")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFormaPagamento> cadastro(@RequestBody @Valid DadosCadastroFormaPagamento dados, UriComponentsBuilder uriBuilder) {
        var formaPagamento = new FormaPagamento(dados);
        repository.save(formaPagamento);

        var uri = uriBuilder.path("/{id}").buildAndExpand(formaPagamento.getIdFormaPagamento()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoFormaPagamento(formaPagamento));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFormaPagamento> atualizar(@RequestBody @Valid DadosAtualizacaoFormaPagamento dados) {
        var formaPagamento = repository.getReferenceById(dados.idFormaPagamento());
        var formaPagamentoAtualizado = formaPagamento.atualizar(dados);
        return ResponseEntity.ok(formaPagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoFormaPagamento> detalhar(@PathVariable Integer id) {
        var formaPagamento = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoFormaPagamento(formaPagamento));
    }

    @GetMapping()
    public ResponseEntity<Page<DadosListagemFormaPagamento>> detalhar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemFormaPagamento::new);
        return ResponseEntity.ok(page);
    }
}

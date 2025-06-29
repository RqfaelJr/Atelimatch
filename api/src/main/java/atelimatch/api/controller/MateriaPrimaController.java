package atelimatch.api.controller;

import atelimatch.api.domain.materiaprima.*;
import atelimatch.api.domain.servico.DadosListagemServico;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/materiaprima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMateriaPrima> cadastro(@RequestBody @Valid DadosCadastroMateriaPrima dados, UriComponentsBuilder uriBuilder) {
        var materiaPrima = new MateriaPrima(dados);
        repository.save(materiaPrima);

        var uri = uriBuilder.path("/{id}").buildAndExpand(materiaPrima.getIdMateriaPrima()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMateriaPrima(materiaPrima));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMateriaPrima> atualizar(@RequestBody @Valid DadosAtualizacaoMateriaPrima dados) {
        var materiaPrima = repository.getReferenceById(dados.idMateriaPrima());
        var materiaPrimaAtualizado = materiaPrima.atualizar(dados);
        return ResponseEntity.ok(materiaPrimaAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMateriaPrima> detalhar(@PathVariable Integer id) {
        var materiaPrima = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMateriaPrima(materiaPrima));
    }

    @GetMapping()
    public ResponseEntity<Page<DadosListagemMateriaPrima>> detalhar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemMateriaPrima::new);
        return ResponseEntity.ok(page);
    }
    
    @GetMapping("/todas")
    public ResponseEntity<List<DadosDetalhamentoMateriaPrima>> listarTodas() {
        var lista = repository.findAll().stream()
            .map(DadosDetalhamentoMateriaPrima::new)
            .toList();
        return ResponseEntity.ok(lista);
    }


}

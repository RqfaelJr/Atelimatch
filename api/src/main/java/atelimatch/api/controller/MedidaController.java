package atelimatch.api.controller;


import atelimatch.api.domain.medida.*;
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
@RequestMapping("/medida")
public class MedidaController {

    @Autowired
    private MedidaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedida> cadastro(@RequestBody @Valid DadosCadastroMedida dados, UriComponentsBuilder uriBuilder) {
        var medida = new Medida(dados);
        repository.save(medida);

        var uri = uriBuilder.path("/{id}").buildAndExpand(medida.getIdMedida()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedida(medida));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedida> atualizar(@RequestBody @Valid DadosAtualizacaoMedida dados) {
        var medida = repository.getReferenceById(dados.idMedida());
        var medidaAtualizado = medida.atualizar(dados);
        return ResponseEntity.ok(medidaAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedida> detalhar(@PathVariable Integer id) {
        var medida = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedida(medida));
    }

    @GetMapping()
    public ResponseEntity<Page<DadosListagemMedida>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemMedida::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<DadosListagemMedida>> listarTodas() {
        var medidas = repository.findAll().stream()
            .map(DadosListagemMedida::new)
            .toList();
        return ResponseEntity.ok(medidas);
    }




}

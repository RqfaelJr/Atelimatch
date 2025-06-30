package atelimatch.api.controller;

import atelimatch.api.domain.especialidade.*;
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
@RequestMapping("/especialidade")
public class EspecialidadeController {
    @Autowired
    private EspecialidadeRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoEspecialidade> cadastrar(@RequestBody @Valid DadosCadastroEspecialidade dados, UriComponentsBuilder uriBuilder) {
        var especialidade = new Especialidade(dados);
        repository.save(especialidade);

        var uri = uriBuilder.path("/{id}").buildAndExpand(especialidade.getIdEspecialidade()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEspecialidade(especialidade));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoEspecialidade> atualizar(@RequestBody @Valid DadosAtualizacaoEspecialidade dados) {
        var especialidade = repository.getReferenceById(dados.idEspecialidade());
        var especialidadeAtualizado = especialidade.atualizar(dados);
        return ResponseEntity.ok(especialidadeAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoEspecialidade> detalhar(@PathVariable Integer id) {
        var especialidade = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoEspecialidade(especialidade));
    }

    @GetMapping()
    public ResponseEntity<Page<DadosListagemEspecialidade>> detalhar(@PageableDefault(size = 10)Pageable paginacao) {
            var page = repository.findAll(paginacao).map(DadosListagemEspecialidade::new);
            return ResponseEntity.ok(page);
    }

}

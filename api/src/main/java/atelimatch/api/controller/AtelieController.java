package atelimatch.api.controller;

import atelimatch.api.domain.pessoa.atelie.*;
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
@RequestMapping("/atelie")
public class AtelieController {

    @Autowired
    private CadastroAtelie cadastro;

    @Autowired
    private AtelieRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAtelie dados, UriComponentsBuilder uriBuilder) {
        var dto = cadastro.cadastrar(dados);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.idAtelie()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAtelie>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemAtelie::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Integer id) {
        var atelie = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAtelie(atelie));
    }

   
}

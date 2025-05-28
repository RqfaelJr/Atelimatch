package atelimatch.api.controller;

import atelimatch.api.domain.atelie.AtelieRepository;
import atelimatch.api.domain.atelie.CadastroAtelie;
import atelimatch.api.domain.atelie.DadosCadastroAtelie;
import atelimatch.api.domain.atelie.DadosListagemAtelie;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atelie")
public class AtelieController {

    @Autowired
    private CadastroAtelie cadastro;

    @Autowired
    private AtelieRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAtelie dados) {
        var dto = cadastro.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAtelie>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemAtelie::new);
        return ResponseEntity.ok(page);
    }
}

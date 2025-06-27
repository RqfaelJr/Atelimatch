package atelimatch.api.controller;

import atelimatch.api.domain.pessoa.atelie.*;
import atelimatch.api.service.bancoDados.BancoDadosServico;
import atelimatch.api.service.ia.GroqAI;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/atelie")
public class AtelieController {

    @Autowired
    private CadastroAtelie cadastro;

    @Autowired
    private AtelieRepository repository;

    @Autowired
    private GroqAI iaService;
    @Autowired
    private BancoDadosServico bancoDadosServico;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAtelie> cadastrar(@RequestBody @Valid DadosCadastroAtelie dados, UriComponentsBuilder uriBuilder) {
        var dto = cadastro.cadastrar(dados);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.idPessoa()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAtelie>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemAtelie::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoAtelie> detalhar(@PathVariable Integer id) {
        var atelie = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAtelie(atelie));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAtelie> atualizar(@RequestBody @Valid DadosAtualizacaoAtelie dados) {
        var dto = cadastro.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/ia")
//    public ResponseEntity<Page<DadosListagemAtelie>> listarComIA(@PageableDefault(size = 10) Pageable paginacao, @RequestBody String string) throws IOException, InterruptedException {
//        String sql = iaService.consultaAI(string);
//        var page = bancoDadosServico.buscarAtelie(sql, paginacao);
//        return ResponseEntity.ok(page);
//    }

}

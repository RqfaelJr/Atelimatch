package atelimatch.api.controller;

import atelimatch.api.domain.especialidade.DadosListagemEspecialidade;
import atelimatch.api.domain.servico.*;
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
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoServico> cadastro(@RequestBody @Valid DadosCadastroServico dados, UriComponentsBuilder uriBuilder){
        var servico = new Servico(dados);
        repository.save(servico);

        var uri = uriBuilder.path("/{id}").buildAndExpand(servico.getIdServico()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoServico(servico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoServico> atualizar(@RequestBody @Valid DadosAtualizacaoServico dados){
        var servico = repository.getReferenceById(dados.idServico());
        var servicoAtualizado = servico.atualizar(dados);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoServico> detalhar(@PathVariable Integer id) {
        var servico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoServico(servico));
    }

    @GetMapping()
    public ResponseEntity<Page<DadosListagemServico>> detalhar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemServico::new);
        return ResponseEntity.ok(page);
    }
}

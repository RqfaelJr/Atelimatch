package atelimatch.api.controller;

import atelimatch.api.domain.endereco.*;
import atelimatch.api.domain.pessoa.cliente.DadosAtualizacaoCliente;
import atelimatch.api.domain.pessoa.cliente.DadosDetalhamentoCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoEndereco> cadastro(@RequestBody @Valid DadosCadastroEndereco dados, UriComponentsBuilder uriBuilder) {
        var endereco = new Endereco(dados);
        repository.save(endereco);

        var uri = uriBuilder.path("/{id}").buildAndExpand(endereco.getIdEndereco()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEndereco(endereco));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoEndereco> atualizar(@RequestBody @Valid DadosAtualizacaoEndereco dados) {
        var endereco = repository.getReferenceById(dados.idEndereco());
        var enderecoAtualizado = endereco.atualizarDados(dados);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoEndereco> detalhar(@PathVariable Integer id) {
        var endereco = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoEndereco(endereco));
    }
}

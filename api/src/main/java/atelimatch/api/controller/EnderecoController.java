package atelimatch.api.controller;

import atelimatch.api.domain.endereco.DadosCadastroEndereco;
import atelimatch.api.domain.endereco.DadosDetalhamentoEndereco;
import atelimatch.api.domain.endereco.Endereco;
import atelimatch.api.domain.endereco.EnderecoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/estado")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroEndereco dados, UriComponentsBuilder uriBuilder) {
        var endereco = new Endereco(dados);
        repository.save(endereco);

        var uri = uriBuilder.path("/{id}").buildAndExpand(endereco.getIdEndereco()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEndereco(endereco));
    }

}

package atelimatch.api.controller;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.bairro.BairroRepository;
import atelimatch.api.domain.bairro.DadosCadastroBairro;
import atelimatch.api.domain.bairro.DadosDetalhamentoBairro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroBairro dados, UriComponentsBuilder uriBuilder) {
        var bairro = new Bairro(dados);
        repository.save(bairro);

        var uri = uriBuilder.path("/{id}").buildAndExpand(bairro.getIdBairro()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoBairro(bairro));
    }
}

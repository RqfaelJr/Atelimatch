package atelimatch.api.controller;

import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.cidade.CidadeRepository;
import atelimatch.api.domain.cidade.DadosCadastroCIdade;
import atelimatch.api.domain.cidade.DadosDetalhamentoCidade;
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
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroCIdade dados, UriComponentsBuilder uriBuilder){
        var cidade = new Cidade(dados);
        repository.save(cidade);

        var uri = uriBuilder.path("/{id}").buildAndExpand(cidade.getIdCidade()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCidade(cidade));
    }



}

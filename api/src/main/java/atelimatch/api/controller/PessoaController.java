package atelimatch.api.controller;

import atelimatch.api.domain.pessoa.CadastrarNovaPessoa;
import atelimatch.api.domain.pessoa.DadosCadastroPessoa;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private CadastrarNovaPessoa cadastro;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoa dados, UriComponentsBuilder uriBuilder){
        var dto = cadastro.cadastro(dados);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.idPessoa()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }


}

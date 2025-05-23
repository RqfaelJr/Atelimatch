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


@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private CadastrarNovaPessoa cadastro;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoa dados){
        var dto = cadastro.cadastro(dados);
        return ResponseEntity.ok(dto);
    }


}

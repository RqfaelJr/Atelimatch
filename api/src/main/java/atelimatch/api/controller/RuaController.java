package atelimatch.api.controller;

import atelimatch.api.domain.rua.DadosCadastroRua;
import atelimatch.api.domain.rua.DadosDetalhamentoRua;
import atelimatch.api.domain.rua.Rua;
import atelimatch.api.domain.rua.RuaRepository;
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
@RequestMapping("/rua")
public class RuaController {

    @Autowired
    private RuaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroRua dados, UriComponentsBuilder uriBuilder) {
        var rua = new Rua(dados);
        repository.save(rua);

        var uri = uriBuilder.path("/{id}").buildAndExpand(rua.getIdRua()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRua(rua));
    }
}

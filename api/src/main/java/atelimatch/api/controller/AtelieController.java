package atelimatch.api.controller;

import atelimatch.api.domain.atelie.AtelieRepository;
import atelimatch.api.domain.atelie.CadastroAtelie;
import atelimatch.api.domain.atelie.DadosCadastroAtelie;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atelie")
public class AtelieController {

    @Autowired
    private CadastroAtelie cadastro;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAtelie dados) {
        var dto = cadastro.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }
}

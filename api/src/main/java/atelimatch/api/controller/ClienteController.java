package atelimatch.api.controller;

import atelimatch.api.domain.cliente.CadastroDeCliente;
import atelimatch.api.domain.cliente.DadosCadastroCliente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private CadastroDeCliente cadastro;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder){
        var dto = cadastro.cadastrar(dados);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.idCliente()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}

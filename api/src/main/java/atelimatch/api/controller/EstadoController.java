package atelimatch.api.controller;

import atelimatch.api.domain.estado.DadosCadastroEstado;
import atelimatch.api.domain.estado.DadosDetalhamentoEstado;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.estado.EstadoRepository;
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
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroEstado dados, UriComponentsBuilder uriBuilder) {
        var estado = new Estado(dados);
        repository.save(estado);

        var uri = uriBuilder.path("/{id}").buildAndExpand(estado.getIdEstado()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEstado(estado));
    }

}

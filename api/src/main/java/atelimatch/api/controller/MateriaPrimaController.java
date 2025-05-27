package atelimatch.api.controller;


import atelimatch.api.domain.materiaprima.DadosCadastroMateriaPrima;
import atelimatch.api.domain.materiaprima.DadosDetalhamentoMateriaPrima;
import atelimatch.api.domain.materiaprima.MateriaPrima;
import atelimatch.api.domain.materiaprima.MateriaPrimaRepository;
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
@RequestMapping("/materiaprima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroMateriaPrima dados, UriComponentsBuilder uriBuilder) {
        var materiaPrima = new MateriaPrima(dados);
        repository.save(materiaPrima);

        var uri = uriBuilder.path("/{id}").buildAndExpand(materiaPrima.getIdMateriaPrima()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMateriaPrima(materiaPrima));
    }
}

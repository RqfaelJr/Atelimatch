package atelimatch.api.controller;

import atelimatch.api.domain.pessoa.atelie.especialidade.DadosCadastroEspecialidade;
import atelimatch.api.domain.pessoa.atelie.especialidade.DadosDetalhamentoEspecialidade;
import atelimatch.api.domain.pessoa.atelie.especialidade.Especialidade;
import atelimatch.api.domain.pessoa.atelie.especialidade.EspecialidadeRepository;
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
@RequestMapping("/especialidade")
public class EspecialidadeController {
    @Autowired
    private EspecialidadeRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroEspecialidade dados, UriComponentsBuilder uriBuilder) {
        var especialidade = new Especialidade(dados);
        repository.save(especialidade);

        var uri = uriBuilder.path("/{id}").buildAndExpand(especialidade.getIdEspecialidade()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEspecialidade(especialidade));
    }
}

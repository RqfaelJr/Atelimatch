package atelimatch.api.controller;

import atelimatch.api.domain.servico.DadosCadastroServico;
import atelimatch.api.domain.servico.DadosDetalhamentoServico;
import atelimatch.api.domain.servico.Servico;
import atelimatch.api.domain.servico.ServicoRepository;
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
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroServico dados, UriComponentsBuilder uriBuilder){
        var servico = new Servico(dados);
        repository.save(servico);

        var uri = uriBuilder.path("/{id}").buildAndExpand(servico.getIdServico()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoServico(servico));
    }
}

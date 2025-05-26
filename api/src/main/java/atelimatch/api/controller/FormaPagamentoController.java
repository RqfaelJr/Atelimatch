package atelimatch.api.controller;

import atelimatch.api.domain.formapagamento.DadosCadastroFormaPagamento;
import atelimatch.api.domain.formapagamento.DadosDetalhamentoFormaPagamento;
import atelimatch.api.domain.formapagamento.FormaPagamento;
import atelimatch.api.domain.formapagamento.FormaPagamentoRepository;
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
@RequestMapping("/formaPagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroFormaPagamento dados, UriComponentsBuilder uriBuilder) {
        var formaPagamento = new FormaPagamento(dados);
        repository.save(formaPagamento);

        var uri = uriBuilder.path("/id").buildAndExpand(formaPagamento.getIdFormaPagamento()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoFormaPagamento(formaPagamento));
    }
}

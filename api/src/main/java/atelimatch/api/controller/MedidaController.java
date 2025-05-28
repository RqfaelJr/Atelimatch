package atelimatch.api.controller;

import atelimatch.api.domain.medida.DadosCadastroMedida;
import atelimatch.api.domain.medida.DadosDetalhamentoMedida;
import atelimatch.api.domain.medida.Medida;
import atelimatch.api.domain.medida.MedidaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medida")
public class MedidaController {

    @Autowired
    private MedidaRepository repository;

    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroMedida dados, UriComponentsBuilder uriBuilder) {
        var medida = new Medida(dados);
        repository.save(medida);

        var uri = uriBuilder.path("/{id}").buildAndExpand(medida.getIdMedida()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedida(medida));
    }
}

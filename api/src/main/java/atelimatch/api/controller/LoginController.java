package atelimatch.api.controller;

import atelimatch.api.domain.pessoa.DadosAutenticacao;
import atelimatch.api.domain.pessoa.atelie.Atelie;
import atelimatch.api.domain.pessoa.atelie.AtelieRepository;
import atelimatch.api.domain.pessoa.atelie.DadosDetalhamentoAtelie;
import atelimatch.api.domain.pessoa.cliente.Cliente;
import atelimatch.api.domain.pessoa.cliente.ClienteRepository;
import atelimatch.api.domain.pessoa.cliente.DadosDetalhamentoCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AtelieRepository atelieRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados, UriComponentsBuilder uriBuilder) {
        var id = atelieRepository.acharIdComLogin(dados.usuario(), dados.senha());
        if (id != null) {
            Optional<Atelie> atelie = atelieRepository.findById(id);
            if (atelie.isPresent()) {
                var uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
                return ResponseEntity.created(uri).body(new DadosDetalhamentoAtelie(atelie.get()));
            }
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                var uri = uriBuilder.path("/{id}").buildAndExpand(id).toUri();
                return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente.get()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found with ID: " + id);
    }
}

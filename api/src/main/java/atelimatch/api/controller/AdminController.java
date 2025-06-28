package atelimatch.api.controller;
import atelimatch.api.service.bancoDados.BancoDadosServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private BancoDadosServico bancoServico;

    @DeleteMapping("/reset")
    public ResponseEntity<Void> resetarBancoDados() {
        bancoServico.limparBancoDados();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/popular")
    public ResponseEntity<Void> criarBancoDados() throws IOException {
        bancoServico.criarBancoDados();
        return ResponseEntity.noContent().build();
    }
}

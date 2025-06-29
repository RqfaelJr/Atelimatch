package atelimatch.api.controller;
import atelimatch.api.domain.grafico.GraficoDTO;
import atelimatch.api.service.bancoDados.BancoDadosServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/graficos")
    public ResponseEntity<Map<String, GraficoDTO>> graficos(){
        Map<String, GraficoDTO> resposta = new HashMap<>();
        resposta.put("grafico1", bancoServico.buscarQuantidadePedidoAtelie());
        resposta.put("grafico2", bancoServico.valorMedioGastoPorFormaPagamento());
        resposta.put("grafico3", bancoServico.valorVendasPorMesAtelie());
        return ResponseEntity.ok(resposta);
    }


}

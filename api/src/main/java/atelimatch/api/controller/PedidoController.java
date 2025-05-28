package atelimatch.api.controller;

import atelimatch.api.domain.pedido.CadastroPedido;
import atelimatch.api.domain.pedido.DadosCadastroPedido;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private CadastroPedido cadastro;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPedido dados){
        var dto = cadastro.cadastrar(dados);
        return ResponseEntity.ok(dto);
    }
}

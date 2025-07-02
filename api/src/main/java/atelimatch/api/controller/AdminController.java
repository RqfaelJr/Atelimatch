package atelimatch.api.controller;
import atelimatch.api.domain.grafico.GraficoDTO;
import atelimatch.api.domain.grafico.ResultadoConsultaDTO;
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
    public ResponseEntity<Map<String, Object>> graficos() {

        // Consultas SQL usadas
        String sql1 = "SELECT pe.nome_pessoa AS nome_atelie, COUNT(p.id_pedido) AS total_pedido " +
                    "FROM pedido p JOIN atelie a on p.id_atelie = a.id_pessoa JOIN pessoa pe ON a.id_pessoa = pe.id_pessoa " +
                    "GROUP BY pe.nome_pessoa;";

        String sql2 = "SELECT f.nome_forma_pagamento, ROUND(AVG(p.valor_total), 2) AS valor_medio " +
                    "FROM pedido p JOIN forma_pagamento f ON p.id_forma_pagamento = f.id_forma_pagamento " +
                    "GROUP BY f.nome_forma_pagamento;";

        String sql3 = "SELECT TO_CHAR(p.data_entrega, 'MM/YYYY') AS mes_ano, SUM(p.valor_total) AS total_vendas " +
                    "FROM pedido p JOIN atelie a ON p.id_atelie = a.id_pessoa " +
                    "JOIN pessoa pe ON a.id_pessoa = pe.id_pessoa " +
                    "WHERE pe.id_pessoa = 6 " +
                    "GROUP BY TO_CHAR(p.data_entrega, 'MM/YYYY') ORDER BY TO_CHAR(p.data_entrega, 'MM/YYYY');";

        // Dados gráficos (labels + valores)
        GraficoDTO g1 = bancoServico.buscarQuantidadePedidoAtelie(sql1);
        GraficoDTO g2 = bancoServico.valorMedioGastoPorFormaPagamento(sql2);
        GraficoDTO g3 = bancoServico.valorVendasPorMesAtelie(sql3);

        // Resultado completo com colunas para tabelas no frontend
        ResultadoConsultaDTO resultado1 = bancoServico.buscarResultadoCompletoComColunas(sql1);
        ResultadoConsultaDTO resultado2 = bancoServico.buscarResultadoCompletoComColunas(sql2);
        ResultadoConsultaDTO resultado3 = bancoServico.buscarResultadoCompletoComColunas(sql3);

        // Montar resposta combinada (gráficos + sql + resultados)
        Map<String, Object> resposta = new HashMap<>();

        resposta.put("grafico1", Map.of(
            "labels", g1.getLabels(),
            "valores", g1.getValores(),
            "sql", sql1,
            "resultado", resultado1
        ));

        resposta.put("grafico2", Map.of(
            "labels", g2.getLabels(),
            "valores", g2.getValores(),
            "sql", sql2,
            "resultado", resultado2
        ));

        resposta.put("grafico3", Map.of(
            "labels", g3.getLabels(),
            "valores", g3.getValores(),
            "sql", sql3,
            "resultado", resultado3
        ));

        return ResponseEntity.ok(resposta);
    }


    



}

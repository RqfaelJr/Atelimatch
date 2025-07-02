package atelimatch.api.service.bancoDados;

import atelimatch.api.domain.grafico.GraficoDTO;
import atelimatch.api.domain.grafico.ResultadoConsultaDTO;
import atelimatch.api.domain.pessoa.atelie.DadosListagemAtelie;
import atelimatch.api.domain.servico.DadosListagemServico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class BancoDadosServico {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void limparBancoDados() {
        System.out.println("Limpando os dados do banco dados!");
        entityManager.createNativeQuery("DROP TABLE IF EXISTS atelie, atelie_servico, cliente, endereco, especialidade, forma_pagamento, materia_prima, materia_prima_servico, medida, medida_pedido, pedido, pedido_servico, pessoa, servico CASCADE").executeUpdate();
    }

    @Transactional
    public void criarBancoDados() throws IOException {
            String conteudo = new String(getClass().getResourceAsStream("/db/schema.sql").readAllBytes(), StandardCharsets.UTF_8);
            for (String linha : conteudo.split(";")) {
                if (!linha.trim().isEmpty()) {
                    jdbcTemplate.execute(linha.trim());
                }
            }
        }
    public Page<DadosListagemAtelie> buscarAtelie(String sql, Pageable pageable) {
        List<DadosListagemAtelie> resultado = jdbcTemplate.query(sql, (rs, rowNum) -> new DadosListagemAtelie(rs.getInt("id_pessoa"),
            rs.getString("nome_pessoa"), rs.getFloat("nota_avaliacao"), rs.getInt("qntd_notas")));

        return new PageImpl<>(resultado, pageable, resultado.size());
    }

    public List<DadosListagemServico> buscarServicoPorAtelie(Integer id) {

        String sql = "SELECT s.id_servico, s.nome_servico, s.tempo_medio, s.valor_servico " +
                    "FROM servico s " +
                    "INNER JOIN atelie_servico ats ON s.id_servico = ats.id_servico " +
                    "WHERE ats.id_atelie = ?";

        return jdbcTemplate.query(
                    sql,
                    new Object[]{id},
                    (rs, rowNum) -> new DadosListagemServico(rs.getInt("id_servico"), rs.getString("nome_servico"), rs.getInt("tempo_medio"), rs.getFloat("valor_servico"))
        );
    }

    public GraficoDTO buscarQuantidadePedidoAtelie(String sql) {

        List<String> labels = new ArrayList<>();
        List<Double> valores = new ArrayList<>();

        jdbcTemplate.query(sql, rs -> {
            labels.add(rs.getString("nome_atelie"));
            valores.add(rs.getDouble("total_pedido"));
        });

        return new GraficoDTO(labels, valores);
    }

    public GraficoDTO valorMedioGastoPorFormaPagamento(String sql) {

        List<String> labels = new ArrayList<>();
        List<Double> valores = new ArrayList<>();

        jdbcTemplate.query(sql, rs -> {
            labels.add(rs.getString("nome_forma_pagamento"));
            valores.add(rs.getDouble("valor_medio"));
        });

        return new GraficoDTO(labels, valores);
    }

    public GraficoDTO valorVendasPorMesAtelie(String sql) {

        List<String> labels = new ArrayList<>();
        List<Double> valores = new ArrayList<>();

        jdbcTemplate.query(sql, rs -> {
            labels.add(rs.getString("mes_ano"));
            valores.add(rs.getDouble("total_vendas"));
        });

        return new GraficoDTO(labels, valores);
    }

    public ResultadoConsultaDTO buscarResultadoCompletoComColunas(String sql) {
        return jdbcTemplate.query(sql, rs -> {
            List<List<String>> rows = new ArrayList<>();
            List<String> colunas = new ArrayList<>();

            int colCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                colunas.add(rs.getMetaData().getColumnLabel(i));
            }

            // Importante: avança para a primeira linha
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= colCount; i++) {
                    row.add(rs.getString(i));
                }
                rows.add(row);
            }

            return new ResultadoConsultaDTO(colunas, rows);
        });
    }



    

    
}


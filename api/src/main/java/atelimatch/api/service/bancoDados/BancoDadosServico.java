package atelimatch.api.service.bancoDados;

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
import atelimatch.api.domain.servico.Servico;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

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
            String conteudo = new String(Files.readAllBytes(Paths.get("/home/rasa/Desktop/Atelimatch/api/src/main/resources/db/schema.sql")));
            for (String linha : conteudo.split(";")) {
                if (!linha.trim().isEmpty()) {
                    jdbcTemplate.execute(linha.trim());
                }
            }
        }
    public Page<DadosListagemAtelie> buscarAtelie(String sql, Pageable pageable) {
        List<DadosListagemAtelie> resultado = jdbcTemplate.query(sql, (rs, rowNum) -> new DadosListagemAtelie(rs.getInt("id_pessoa"),
            rs.getString("nome_pessoa")));

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
}


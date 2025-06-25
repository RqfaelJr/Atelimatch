package atelimatch.api.service.bancoDados;

import atelimatch.api.domain.pessoa.atelie.Atelie;
import atelimatch.api.domain.pessoa.atelie.DadosListagemAtelie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            String conteudo = new String(Files.readAllBytes(Paths.get("/home/rasa/Desktop/Atelimatch/api/src/main/resources/db/schema.sql")));
            for (String linha : conteudo.split(";")) {
                if (!linha.trim().isEmpty()) {
                    jdbcTemplate.execute(linha.trim());
                }
            }
        }
    public Page<DadosListagemAtelie> buscarAtelie(String sql, Pageable pageable) {
        List<DadosListagemAtelie> resultado = jdbcTemplate.query(sql + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(), (rs, rowNum) -> new DadosListagemAtelie(
                rs.getString("nome_pessoa")));

        return new PageImpl<>(resultado, pageable, resultado.size());
    }
}


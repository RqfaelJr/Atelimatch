package atelimatch.api.domain.bancoDados;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
    }


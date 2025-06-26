package atelimatch.api.domain.pessoa.atelie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AtelieRepository extends JpaRepository<Atelie, Integer> {

    @Query("Select p.idPessoa from Pessoa as p where p.usuario = :usuario and p.senha = :senha")
    Integer acharIdComLogin(String usuario, String senha);
}

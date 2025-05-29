package atelimatch.api.domain.pessoa;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.rua.Rua;
import atelimatch.api.domain.bairro.Bairro;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Pessoa {
    protected Integer idPessoa;
    protected String nomePessoa;
    protected String email;
    protected String senha;
    protected String usuario;
    protected String telefone;
    protected String cnpj;
    protected Estado estado;
    protected Cidade cidade;
    protected Bairro bairro;
    protected Rua rua;


}

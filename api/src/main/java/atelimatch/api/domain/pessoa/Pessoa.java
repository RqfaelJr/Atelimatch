package atelimatch.api.domain.pessoa;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.rua.Rua;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass // Mudar para entity e @Inheritance(strategy = InheritanceType.JOINED)
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

    protected void atualizarDadosComuns(String nomePessoa, String email, String senha, String usuario, String telefone, Bairro bairro, Rua rua) {
        if (nomePessoa != null) this.nomePessoa = nomePessoa;
        if (email != null) this.email = email;
        if (senha != null) this.senha = senha;
        if (usuario != null) this.usuario = usuario;
        if (telefone != null) this.telefone = telefone;
        if (bairro != null) this.bairro = bairro;
        if (rua != null) this.rua = rua;
    }
}

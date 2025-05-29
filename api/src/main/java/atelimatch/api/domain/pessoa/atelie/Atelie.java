package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.pessoa.Pessoa;
import atelimatch.api.domain.rua.Rua;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Atelie extends Pessoa{
    private Integer idAtelie;

    public Atelie(String nomePessoa, String email, String senha, String usuario, String telefone, Estado estado, Cidade cidade, Bairro bairro, Rua rua) {
        this.nomePessoa = nomePessoa;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void atualizar(DadosAtualizacaoAtelie dados, Bairro bairro, Rua rua) {
        atualizarDadosComuns(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), bairro, rua);
    }
}

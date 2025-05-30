package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.pessoa.Pessoa;
import atelimatch.api.domain.rua.Rua;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Atelie extends Pessoa{
    private Float notaAtelie;
    private Integer qntdNotas;


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
        if (dados.notaAtelie() != null) {
            this.notaAtelie = (this.notaAtelie * this.qntdNotas + dados.notaAtelie()) / (this.qntdNotas + 1);
            this.qntdNotas++;
        }

    }
}

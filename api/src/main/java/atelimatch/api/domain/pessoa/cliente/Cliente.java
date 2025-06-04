package atelimatch.api.domain.pessoa.cliente;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.endereco.Endereco;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.pessoa.Pessoa;
import atelimatch.api.domain.rua.Rua;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa{
    private String cpf;
    private LocalDate dataNascimento;

    public Cliente(String nomePessoa, String email, String senha, String usuario, String telefone, Endereco endereco, String cpf, LocalDate dataNascimento) {
        this.nomePessoa = nomePessoa;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public void atualizar(DadosAtualizacaoCliente dados, Endereco endereco) {
        atualizarDadosComuns(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), endereco);

    }
}

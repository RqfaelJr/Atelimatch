package atelimatch.api.domain.pessoa.cliente;


import atelimatch.api.domain.endereco.Endereco;
import atelimatch.api.domain.pessoa.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_pessoa")
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
        atualizarDadosComuns(dados.nomePessoa(), dados.senha(), dados.usuario(), dados.telefone(), endereco);

    }
}

package atelimatch.api.domain.pessoa.cliente;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.pessoa.Pessoa;

import atelimatch.api.domain.rua.Rua;
import lombok.*;


import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa{
    private String cpf;
    private LocalDate dataNascimento;

    public Cliente(String nomePessoa, String email, String senha, String usuario, String telefone, Estado estado, Cidade cidade, Bairro bairro, Rua rua, String cpf,  LocalDate dataNascimento) {
        this.nomePessoa = nomePessoa;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public void atualizar(DadosAtualizacaoCliente dados, Bairro bairro, Rua rua, Estado estado, Cidade cidade) {
        atualizarDadosComuns(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), bairro, rua);

        if (estado != null) this.estado = estado;
        if (cidade != null) this.cidade = cidade;
    }
}

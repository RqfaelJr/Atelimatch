package atelimatch.api.domain.pessoa.atelie;


import atelimatch.api.domain.endereco.Endereco;
import atelimatch.api.domain.pessoa.Pessoa;
import atelimatch.api.domain.especialidade.Especialidade;
import atelimatch.api.domain.servico.Servico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Atelie extends Pessoa{
    private Float notaAvaliacao;
    private Integer qntdNotas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEspecialidade")
    private Especialidade especialidade;
    @Column(name = "inicio_01")
    private Integer inicio01;
    @Column(name = "fim_01")
    private Integer fim01;

    @ManyToMany
    @JoinTable(
            name = "atelie_servico",
            joinColumns = @JoinColumn(name = "id_atelie"),
            inverseJoinColumns = @JoinColumn(name = "id_servico")
    )
    private Set<Servico> servicos = new HashSet<>();


    public Atelie(String nomePessoa, String email, String senha, String usuario, String telefone, Endereco endereco, String cnpj, Especialidade especialidade, Integer inicio01, Integer fim01, Integer inicio02, Integer fim02) {
        this.nomePessoa = nomePessoa;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.especialidade = especialidade;
        this.notaAvaliacao = 0.0f;
        this.qntdNotas = 0;
        this.inicio01 = inicio01;
        this.fim01 = fim01;
    }

    public void atualizar(DadosAtualizacaoAtelie dados, Endereco endereco) {
        atualizarDadosComuns(dados.nomePessoa(), dados.senha(), dados.usuario(), dados.telefone(), endereco);
        if (dados.notaAtelie() != null) {
            this.notaAvaliacao = (this.notaAvaliacao * this.qntdNotas + dados.notaAtelie()) / (this.qntdNotas + 1);
            this.qntdNotas++;
        }
        if (dados.inicio01() != null) this.inicio01 = dados.inicio01();
        if (dados.fim01() != null) this.inicio01 = dados.fim01();

    }
}

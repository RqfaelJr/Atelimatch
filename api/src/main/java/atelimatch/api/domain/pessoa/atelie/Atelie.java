package atelimatch.api.domain.pessoa.atelie;


import atelimatch.api.domain.endereco.Endereco;
import atelimatch.api.domain.pessoa.Pessoa;
import atelimatch.api.domain.pessoa.atelie.especialidade.Especialidade;
import atelimatch.api.domain.servico.Servico;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Atelie extends Pessoa{
    private Float notaAvaliacao;
    private Integer qntdNotas;
    private Especialidade especialidade;
    private Integer inicio01;
    private Integer fim01;
    private Integer inicio02;
    private Integer fim02;

    @ManyToMany
    @JoinTable(
            name = "AtelieServico",
            joinColumns = @JoinColumn(name = "idAtelie"),
            inverseJoinColumns = @JoinColumn(name = "idServico")
    )
    private Set<Servico> servicos = new HashSet<>();


    public Atelie(String nomePessoa, String email, String senha, String usuario, String telefone, Endereco endereco, Especialidade especialidade, Set<Servico> servicos, Integer inicio01, Integer fim01, Integer inicio02, Integer fim02) {
        this.nomePessoa = nomePessoa;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
        this.endereco = endereco;
        this.especialidade = especialidade;
        this.notaAvaliacao = 0.0f;
        this.qntdNotas = 0;
        this.servicos = servicos;
        this.inicio01 = inicio01;
        this.fim01 = fim01;
        this.inicio02 = inicio02;
        this.fim02 = fim02;

    }

    public void atualizar(DadosAtualizacaoAtelie dados, Endereco endereco) {
        atualizarDadosComuns(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), endereco);
        if (dados.notaAtelie() != null) {
            this.notaAvaliacao = (this.notaAvaliacao * this.qntdNotas + dados.notaAtelie()) / (this.qntdNotas + 1);
            this.qntdNotas++;
        }
        if (dados.inicio01() != null) this.inicio01 = dados.inicio01();
        if (dados.fim01() != null) this.inicio01 = dados.fim01();
        this.inicio02 = dados.inicio02();
        this.fim02 = dados.inicio02();

    }
}

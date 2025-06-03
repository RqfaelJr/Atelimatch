package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.bairro.Bairro;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.pessoa.Pessoa;
import atelimatch.api.domain.pessoa.atelie.especialidade.Especialidade;
import atelimatch.api.domain.rua.Rua;
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

    @ManyToMany
    @JoinTable(
            name = "AtelieServico",
            joinColumns = @JoinColumn(name = "idAtelie"),
            inverseJoinColumns = @JoinColumn(name = "idServico")
    )
    private Set<Servico> servicos = new HashSet<>();


    public Atelie(String nomePessoa, String email, String senha, String usuario, String telefone, Estado estado, Cidade cidade, Bairro bairro, Rua rua, Especialidade especialidade, Set<Servico> servicos) {
        this.nomePessoa = nomePessoa;
        this.email = email;
        this.senha = senha;
        this.usuario = usuario;
        this.telefone = telefone;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.especialidade = especialidade;
        this.notaAvaliacao = 0.0f;
        this.qntdNotas = 0;
        this.servicos = servicos;
    }

    public void atualizar(DadosAtualizacaoAtelie dados, Bairro bairro, Rua rua) {
        atualizarDadosComuns(dados.nomePessoa(), dados.email(), dados.senha(), dados.usuario(), dados.telefone(), bairro, rua);
        if (dados.notaAtelie() != null) {
            this.notaAvaliacao = (this.notaAvaliacao * this.qntdNotas + dados.notaAtelie()) / (this.qntdNotas + 1);
            this.qntdNotas++;
        }

    }
}

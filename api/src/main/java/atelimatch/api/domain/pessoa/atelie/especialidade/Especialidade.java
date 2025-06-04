package atelimatch.api.domain.pessoa.atelie.especialidade;


import atelimatch.api.domain.pessoa.cliente.DadosDetalhamentoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Especialidade {
    private Integer idEspecialidade;
    private String descricaoEspecialidade;

    public Especialidade(DadosCadastroEspecialidade dados) {
        this.descricaoEspecialidade = dados.descricaoEspecialidade();
    }

    public DadosDetalhamentoEspecialidade atualizar(DadosAtualizacaoEspecialidade dados) {
        this.descricaoEspecialidade = dados.descricaoEspecialidade();
        return new DadosDetalhamentoEspecialidade(this);
    }
}

package atelimatch.api.domain.especialidade;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Especialidade")
@Entity(name = "Especialidade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEspecialidade")
public class Especialidade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

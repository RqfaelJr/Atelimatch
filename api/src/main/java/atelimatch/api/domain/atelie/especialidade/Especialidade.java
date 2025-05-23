package atelimatch.api.domain.atelie.especialidade;


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
}

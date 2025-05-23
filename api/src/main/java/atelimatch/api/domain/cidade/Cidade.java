package atelimatch.api.domain.cidade;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {
    private Integer idCidade;
    private String nomeCidade;

    public Cidade(DadosCadastroCIdade dados) {
        this.nomeCidade = dados.nomeCidade();
    }
}

package atelimatch.api.domain.materiaprima;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrima {
    private Integer idMateriaPrima;
    private String nomeMateriaPrima;
    private Float qtdMateriaPrima;
    private String unidadeMateriaPrima;

    public MateriaPrima(DadosCadastroMateriaPrima  dados) {
        this.nomeMateriaPrima = dados.nomeMateriaPrima();
        this.qtdMateriaPrima = dados.qtdeMateriaPrima();
        this.unidadeMateriaPrima = dados.unidadeMateriaPrima();
    }
}

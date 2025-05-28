package atelimatch.api.domain.medida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medida {
    private Integer idMedida;
    private String categoria;
    private Integer valorMedida;

    public Medida(DadosCadastroMedida dados) {
        this.categoria =dados.categoria();
        this.valorMedida = dados.valorMedida();
    }
}

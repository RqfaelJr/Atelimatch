package atelimatch.api.domain.materiaprima;

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
}

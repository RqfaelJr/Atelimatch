package atelimatch.api.domain.servico;

import atelimatch.api.domain.materiaprima.MateriaPrima;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrimaServico {
    private Servico Servico;
    private MateriaPrima materiaPrima;
    private Float valor;
    private String unidadeMedida;

}

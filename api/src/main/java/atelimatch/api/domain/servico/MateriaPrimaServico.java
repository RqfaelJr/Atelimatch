package atelimatch.api.domain.servico;

import atelimatch.api.domain.materiaprima.MateriaPrima;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrimaServico {

    @EmbeddedId
    private MateriaPrimaServicoId idMateriaPrimaServico;

    @ManyToOne
    @MapsId("idServico")
    private Servico Servico;

    @ManyToOne
    @MapsId("idMateriaPrima")
    private MateriaPrima materiaPrima;
    private Float valor;
    private String unidadeMedida;

}

package atelimatch.api.domain.servico;

import atelimatch.api.domain.materiaprima.MateriaPrima;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MateriaPrimaServico")
@Entity(name = "MateriaPrimaServico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrimaServico {

    @EmbeddedId
    private MateriaPrimaServicoId idMateriaPrimaServico;

    @ManyToOne
    @MapsId("idServico")
    private Servico servico;

    @ManyToOne
    @MapsId("idMateriaPrima")
    private MateriaPrima materiaPrima;
    private Float valor;
    private String unidadeMedida;

}

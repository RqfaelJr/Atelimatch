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
    @JoinColumn(name = "id_servico")
    private Servico servico;

    @ManyToOne
    @MapsId("idMateriaPrima")
    @JoinColumn(name = "id_materia_prima")
    private MateriaPrima materiaPrima;
    @Column(name = "valor_materia_servico")
    private Float valor;
    @Column(name = "unidade_medida_materia_servico")
    private String unidadeMedida;

}

package atelimatch.api.domain.servico;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class MateriaPrimaServicoId implements Serializable {
    private Integer idServico;
    private Integer idMateriaPrima;
}

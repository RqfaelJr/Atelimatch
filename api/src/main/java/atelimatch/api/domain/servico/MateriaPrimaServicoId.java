package atelimatch.api.domain.servico;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrimaServicoId implements Serializable {
    private Integer idServico;
    private Integer idMateriaPrima;
}

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
    private String valorMedida;
}

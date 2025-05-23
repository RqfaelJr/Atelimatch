package atelimatch.api.domain.estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    private Integer idEstado;
    private String UF;
    private String nomeEstado;

}

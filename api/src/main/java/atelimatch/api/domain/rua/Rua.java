package atelimatch.api.domain.rua;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rua {
    private Integer idRua;
    private String nomeRua;
    private Integer numeroRua;
    private String complemento;

}

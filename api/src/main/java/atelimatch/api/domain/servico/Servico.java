package atelimatch.api.domain.servico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
    private Integer idServico;
    private Integer tempoMedio;
    private Float valorServico;

}

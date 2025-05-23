package atelimatch.api.domain.atelie;

import atelimatch.api.domain.atelie.especialidade.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AtelieEspecialidade {
    private Atelie atelie;
    private Especialidade especialidade;
}

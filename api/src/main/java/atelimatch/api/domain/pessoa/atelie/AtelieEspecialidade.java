package atelimatch.api.domain.pessoa.atelie;

import atelimatch.api.domain.pessoa.atelie.especialidade.Especialidade;
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

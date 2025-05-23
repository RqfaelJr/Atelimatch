package atelimatch.api.domain.atelie;

import atelimatch.api.domain.pessoa.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Atelie {
    private Integer idAtelie;
    private Pessoa pessoa;

}

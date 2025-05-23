package atelimatch.api.domain.atelie;

import atelimatch.api.domain.servico.Servico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AtelieServico {
    private Atelie atelie;
    private Servico servico;
}

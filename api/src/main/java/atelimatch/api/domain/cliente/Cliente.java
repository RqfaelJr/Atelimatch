package atelimatch.api.domain.cliente;

import atelimatch.api.domain.pessoa.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Integer idCliente;
    private String cpf;
    private LocalDateTime dataNascimento;
    private Pessoa pessoa;
}

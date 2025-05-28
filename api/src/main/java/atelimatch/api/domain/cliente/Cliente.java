package atelimatch.api.domain.cliente;

import atelimatch.api.domain.pessoa.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Integer idCliente;
    private String cpf;
    private LocalDate dataNascimento;
    private Pessoa pessoa;
}

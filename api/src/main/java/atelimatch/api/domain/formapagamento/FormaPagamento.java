package atelimatch.api.domain.formapagamento;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamento {
    private Integer idFormaPagamento;
    private String nomeFormaPagamento;

    public FormaPagamento(DadosCadastroFormaPagamento dados) {
        this.nomeFormaPagamento = dados.nomeFormaPagamento();
    }
}

package atelimatch.api.domain.formapagamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "FormaPagamento")
@Entity(name = "FormaPagamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idFormaPagamento")
public class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormaPagamento;
    private String nomeFormaPagamento;

    public FormaPagamento(DadosCadastroFormaPagamento dados) {
        this.nomeFormaPagamento = dados.nomeFormaPagamento();
    }

    public DadosDetalhamentoFormaPagamento atualizar(DadosAtualizacaoFormaPagamento dados) {
        this.nomeFormaPagamento = dados.nomeFormaPagamento();
        return new DadosDetalhamentoFormaPagamento(this);
    }
}

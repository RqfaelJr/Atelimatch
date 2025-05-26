package atelimatch.api.domain.formapagamento;

public record DadosDetalhamentoFormaPagamento(Integer idFormaPagamento, String nomeFormaPagamento) {
    public DadosDetalhamentoFormaPagamento(FormaPagamento formaPagamento) {
        this(formaPagamento.getIdFormaPagamento(), formaPagamento.getNomeFormaPagamento());
    }
}

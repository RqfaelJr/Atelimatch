package atelimatch.api.domain.formapagamento;

public record DadosListagemFormaPagamento(Integer idFormaPagamento, String nomeFormaPagamento) {
    public DadosListagemFormaPagamento(FormaPagamento formaPagamento) {
        this(formaPagamento.getIdFormaPagamento(), formaPagamento.getNomeFormaPagamento());
    }
}

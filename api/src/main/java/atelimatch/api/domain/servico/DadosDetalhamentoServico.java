package atelimatch.api.domain.servico;

public record DadosDetalhamentoServico(Integer idServico, String nomeServico, Integer tempoMedio, Float valorServico) {
    public DadosDetalhamentoServico(Servico servico) {
        this(servico.getIdServico(), servico.getNomeServico(), servico.getTempoMedio(), servico.getValorServico());
    }
}

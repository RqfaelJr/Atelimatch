package atelimatch.api.domain.servico;



public record DadosListagemServico(Integer idServico, String nome, Integer tempoMedio, Float valorServico) {
    public DadosListagemServico(Servico servico) {
        this(servico.getIdServico(), servico.getNomeServico(), servico.getTempoMedio(), servico.getValorServico());
    }


}

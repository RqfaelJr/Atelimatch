package atelimatch.api.domain.servico;

public record DadosListagemServico(Integer idServico, String nome) {
    public DadosListagemServico(Servico servico) {
        this(servico.getIdServico(), servico.getNomeServico());
    }
}

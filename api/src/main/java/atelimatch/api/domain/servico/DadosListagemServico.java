package atelimatch.api.domain.servico;

import java.util.Map;

public record DadosListagemServico(Integer idServico, String nome, Integer tempoMedio, Float valorServico) {
    public DadosListagemServico(Servico servico) {
        this(servico.getIdServico(), servico.getNomeServico(), servico.getTempoMedio(), servico.getValorServico());
    }


}

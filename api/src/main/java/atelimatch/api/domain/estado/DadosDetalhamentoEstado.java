package atelimatch.api.domain.estado;

public record DadosDetalhamentoEstado(Integer id, String UF, String nomeEstado) {
    public DadosDetalhamentoEstado(Estado estado) {
        this(estado.getIdEstado(), estado.getUF(), estado.getNomeEstado());
    }
}

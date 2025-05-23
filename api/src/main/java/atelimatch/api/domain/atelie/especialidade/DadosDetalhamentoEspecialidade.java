package atelimatch.api.domain.atelie.especialidade;

public record DadosDetalhamentoEspecialidade(Integer idEspecialidade, String descricaoEspecialidade) {

    public DadosDetalhamentoEspecialidade(Especialidade especialidade) {
        this(especialidade.getIdEspecialidade(), especialidade.getDescricaoEspecialidade());
    }
}

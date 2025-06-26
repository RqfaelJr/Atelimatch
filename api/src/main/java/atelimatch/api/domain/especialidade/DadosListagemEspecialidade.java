package atelimatch.api.domain.especialidade;

public record DadosListagemEspecialidade(Integer idEspecialidade, String nome) {
    public DadosListagemEspecialidade(Especialidade especialidade) {
        this(especialidade.getIdEspecialidade(), especialidade.getDescricaoEspecialidade());
    }
}

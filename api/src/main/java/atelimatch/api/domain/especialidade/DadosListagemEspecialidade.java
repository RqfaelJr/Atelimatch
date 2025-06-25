package atelimatch.api.domain.especialidade;

public record DadosListagemEspecialidade(String nome) {
    public DadosListagemEspecialidade(Especialidade especialidade) {
        this(especialidade.getDescricaoEspecialidade());
    }
}

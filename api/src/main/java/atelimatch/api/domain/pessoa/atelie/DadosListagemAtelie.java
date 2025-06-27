package atelimatch.api.domain.pessoa.atelie;

public record DadosListagemAtelie(Integer idAtelie, String nome) {
    public DadosListagemAtelie(Atelie atelie) {
        this(atelie.getIdPessoa(), atelie.getNomePessoa());
    }
}

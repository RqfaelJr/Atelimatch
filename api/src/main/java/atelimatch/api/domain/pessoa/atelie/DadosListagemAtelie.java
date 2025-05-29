package atelimatch.api.domain.pessoa.atelie;

public record DadosListagemAtelie(String nome) {
    public DadosListagemAtelie(Atelie atelie) {
        this(atelie.getPessoa().getNomePessoa());
    }
}

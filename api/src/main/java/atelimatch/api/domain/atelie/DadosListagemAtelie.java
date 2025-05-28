package atelimatch.api.domain.atelie;

public record DadosListagemAtelie(String nome) {
    public DadosListagemAtelie(Atelie atelie) {
        this(atelie.getPessoa().getNomePessoa());
    }
}

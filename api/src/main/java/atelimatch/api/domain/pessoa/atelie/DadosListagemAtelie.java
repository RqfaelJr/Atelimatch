package atelimatch.api.domain.pessoa.atelie;

public record DadosListagemAtelie(Integer idAtelie, String nome, Float nota, Integer qtdNotas) {
    public DadosListagemAtelie(Atelie atelie) {
        this(atelie.getIdPessoa(), atelie.getNomePessoa(), atelie.getNotaAvaliacao(), atelie.getQntdNotas());
    }
}

package atelimatch.api.domain.atelie;

public record DadosDetalhamentoAtelie(Integer idAtelie, Integer idPessoa) {
    public DadosDetalhamentoAtelie(Atelie atelie) {
        this(atelie.getIdAtelie(), atelie.getPessoa().getIdPessoa());
    }
}

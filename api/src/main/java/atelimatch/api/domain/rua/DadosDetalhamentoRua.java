package atelimatch.api.domain.rua;

public record DadosDetalhamentoRua(Integer idRua, String nomeRua, Integer numeroRua, String complemento) {
    public DadosDetalhamentoRua(Rua rua) {
        this(rua.getIdRua(), rua.getNomeRua(), rua.getNumeroRua(), rua.getComplemento());
    }
}

package atelimatch.api.domain.cidade;

public record DadosDetalhamentoCidade(Integer id, String nomeCidade) {
    public DadosDetalhamentoCidade(Cidade cidade) {
        this(cidade.getIdCidade(), cidade.getNomeCidade());
    }
}

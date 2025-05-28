package atelimatch.api.domain.medida;

public record DadosDetalhamentoMedida(Integer idMedida, String categoria, Integer valorMedida) {

    public DadosDetalhamentoMedida(Medida medida) {
        this(medida.getIdMedida(), medida.getCategoria(), medida.getValorMedida());
    }
}

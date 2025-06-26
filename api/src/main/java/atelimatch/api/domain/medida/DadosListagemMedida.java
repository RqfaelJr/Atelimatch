package atelimatch.api.domain.medida;

public record DadosListagemMedida(Integer idMedida, String nome, Integer valor) {
    public DadosListagemMedida(Medida medida) {
        this(medida.getIdMedida(), medida.getCategoria(), medida.getValorMedida());
    }
}

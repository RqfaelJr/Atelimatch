package atelimatch.api.domain.materiaprima;

public record DadosListagemMateriaPrima(Integer idMateriaPrima, String nome) {
    public DadosListagemMateriaPrima(MateriaPrima materiaPrima) {
        this(materiaPrima.getIdMateriaPrima(), materiaPrima.getNomeMateriaPrima());
    }
}

package atelimatch.api.domain.materiaprima;

public record DadosDetalhamentoMateriaPrima(Integer idMateriaPrima, String nomeMateriaPrima, Float qtdeMateriaPrima, String unidadeMateriaPrima) {
    public DadosDetalhamentoMateriaPrima(MateriaPrima materiaPrima) {
        this(materiaPrima.getIdMateriaPrima(), materiaPrima.getNomeMateriaPrima(), materiaPrima.getQtdeMateriaPrima(), materiaPrima.getUnidadeMateriaPrima());
    }
}

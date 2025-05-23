package atelimatch.api.domain.bairro;

public record DadosDetalhamentoBairro(Integer idBairro, String nomeBairro) {

    public DadosDetalhamentoBairro(Bairro bairro) {
        this(bairro.getIdBairro(), bairro.getNomeBairro());
    }
}

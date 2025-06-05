package atelimatch.api.domain.endereco;

public record DadosDetalhamentoEndereco(Integer idEndereco, String rua, Integer numero, String bairro, String cidade, String estado, String cep, String complemento) {
    public DadosDetalhamentoEndereco(Endereco endereco) {
        this(endereco.getIdEndereco(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), endereco.getComplemento());
    }
}

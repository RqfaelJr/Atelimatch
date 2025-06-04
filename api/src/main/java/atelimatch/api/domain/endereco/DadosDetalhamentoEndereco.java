package atelimatch.api.domain.endereco;

public record DadosDetalhamentoEndereco(Integer idEndereco, String rua, String numero, String bairro, String cidade, String estado, String cep) {
    public DadosDetalhamentoEndereco(Endereco endereco) {
        this(endereco.getIdEndereco(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
    }
}

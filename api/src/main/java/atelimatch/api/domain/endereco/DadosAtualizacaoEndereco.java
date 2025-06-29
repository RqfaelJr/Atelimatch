package atelimatch.api.domain.endereco;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoEndereco(
        @NotNull
        Integer idEndereco,
        String rua,

        String bairro,
        @Pattern(regexp = "\\d{8}")
        String cep,
        String cidade,
        String estado,
        String complemento,
        Integer numero
) {
}

package atelimatch.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(@NotBlank
                                    String estado,
                                    @NotBlank
                                    String uf,
                                    @NotBlank
                                    String cidade,
                                    @NotBlank
                                    String bairro,
                                    @NotBlank
                                    String rua,
                                    @NotBlank
                                    @Pattern(regexp = "\\d{8}")
                                    String cep,
                                    Integer numero,
                                    String complemento
                                    ) {
}

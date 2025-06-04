package atelimatch.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(@NotBlank
                                    String rua,
                                    @NotBlank
                                    String bairro,
                                    @NotBlank
                                    @Pattern(regexp = "\\d{8}")
                                    String cep,
                                    @NotBlank
                                    String cidade,
                                    @NotBlank
                                    String uf,
                                    @NotBlank
                                    String estado,
                                    String complemento,
                                    String numero) {
}

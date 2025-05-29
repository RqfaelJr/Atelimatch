package atelimatch.api.domain.pessoa.atelie;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAtelie(
        @NotBlank
        String nomePessoa,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String usuario,
        @NotBlank
        String telefone,
        @Pattern(regexp = "^\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}$\n")
        String cnpj,
        @NotBlank
        Integer idEstado,
        @NotBlank
        Integer idCidade,
        @NotBlank
        Integer idBairro,
        @NotBlank
        Integer idRua
) {
}

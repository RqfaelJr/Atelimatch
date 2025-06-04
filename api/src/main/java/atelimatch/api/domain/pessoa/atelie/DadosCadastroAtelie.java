package atelimatch.api.domain.pessoa.atelie;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

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
        @NotNull
        Integer idEndereco,
        @NotNull
        Integer idEspecialidade,
        @NotNull
        List<Integer> idsServico,
        @NotBlank
        Integer inicio01,
        @NotBlank
        Integer fim01,
        Integer inicio02,
        Integer fim02
) {
}

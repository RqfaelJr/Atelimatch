package atelimatch.api.domain.pessoa.atelie;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroAtelie(
        @NotBlank
        String nomePessoa,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String usuario,
        @NotBlank
        String senha,
        String cnpj,
        @NotNull
        Integer idEndereco,
        @NotNull
        Integer idEspecialidade,
        @NotNull
        List<Integer> idsServico,
        @NotNull
        Integer inicio01,
        @NotNull
        Integer fim01,
        Integer inicio02,
        Integer fim02
) {
}

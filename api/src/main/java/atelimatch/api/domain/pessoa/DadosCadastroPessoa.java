package atelimatch.api.domain.pessoa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPessoa(
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

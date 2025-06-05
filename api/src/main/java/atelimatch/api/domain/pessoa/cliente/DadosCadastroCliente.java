package atelimatch.api.domain.pessoa.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DadosCadastroCliente(

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
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")
        String cpf,
        @NotNull
        LocalDate dataNascimento

) {
}

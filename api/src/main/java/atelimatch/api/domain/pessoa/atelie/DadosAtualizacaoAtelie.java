package atelimatch.api.domain.pessoa.atelie;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAtelie(

        @NotNull
        Integer idPessoa,
        String nomePessoa,
        @Email
        String email,
        String senha,
        String usuario,
        String telefone,
        Integer idBairro,
        Integer idRua,
        Float notaAtelie,
        Integer idEspecialidade

) {
}

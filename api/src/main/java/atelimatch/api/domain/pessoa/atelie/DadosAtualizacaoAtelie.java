package atelimatch.api.domain.pessoa.atelie;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizacaoAtelie(

        @NotNull
        Integer idPessoa,
        String nomePessoa,
        @Email
        String email,
        String senha,
        String usuario,
        String telefone,
        Integer idEndereco,
        Float notaAtelie,
        Integer idEspecialidade,
        List<Integer> idsServico,
        Integer inicio01,
        Integer fim01,
        Integer inicio02,
        Integer fim02

) {
}

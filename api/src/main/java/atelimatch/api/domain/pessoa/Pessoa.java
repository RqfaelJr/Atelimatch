package atelimatch.api.domain.pessoa;
import atelimatch.api.domain.cidade.Cidade;
import atelimatch.api.domain.estado.Estado;
import atelimatch.api.domain.rua.Rua;
import atelimatch.api.domain.bairro.Bairro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    private Integer idPessoa;
    private String nomePessoa;
    private String email;
    private String senha;
    private String usuario;
    private String telefone;
    private Estado estado;
    private Cidade cidade;
    private Bairro bairro;
    private Rua rua;


}

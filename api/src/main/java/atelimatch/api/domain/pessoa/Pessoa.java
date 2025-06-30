package atelimatch.api.domain.pessoa;


import atelimatch.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Pessoa")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPessoa")
@Inheritance(strategy = InheritanceType.JOINED) // Mudar para entity e @Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_pessoa")
    protected Integer idPessoa;
    protected String nomePessoa;
    protected String email;
    protected String senha;
    protected String usuario;
    protected String telefone;
    protected String cnpj;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEndereco")
    protected Endereco endereco;

    protected void atualizarDadosComuns(String nomePessoa, String senha, String usuario, String telefone, Endereco endereco) {
        if (nomePessoa != null) this.nomePessoa = nomePessoa;
        if (senha != null) this.senha = senha;
        if (usuario != null) this.usuario = usuario;
        if (telefone != null) this.telefone = telefone;
        if (endereco != null) this.endereco = endereco;

    }
}

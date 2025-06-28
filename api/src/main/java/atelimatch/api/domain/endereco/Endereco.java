package atelimatch.api.domain.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Endereco")
@Entity(name = "Endereco")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEndereco")
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;
    private String rua;
    private String bairro;
    private String cep;
    private String uf;
    private String estado;
    private String cidade;
    private String complemento;
    private Integer numero;

    public Endereco(DadosCadastroEndereco dados) {
        this.rua = dados.rua();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.estado = dados.estado();
        this.cidade = dados.cidade();
        this.complemento = dados.complemento();
        this.numero = dados.numero();
    }

    public void atualizarDados(DadosAtualizacaoEndereco dados) {
        if (dados.rua() != null) {
            this.rua = dados.rua();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.estado() != null) {
            this.estado = dados.estado();
        }

        this.cidade = dados.cidade();

        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
    }
}



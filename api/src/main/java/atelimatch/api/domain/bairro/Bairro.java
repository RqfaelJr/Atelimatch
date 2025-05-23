package atelimatch.api.domain.bairro;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {
    private Integer idBairro;
    private String nomeBairro;

    public Bairro(DadosCadastroBairro dados) {
        this.nomeBairro = dados.nomeBairro();
    }
}

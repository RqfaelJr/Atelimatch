package atelimatch.api.domain.estado;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    private Integer idEstado;
    private String UF;
    private String nomeEstado;

    public Estado(DadosCadastroEstado dados) {
        this.UF = dados.UF();
        this.nomeEstado = dados.nomeEstado();
    }
}

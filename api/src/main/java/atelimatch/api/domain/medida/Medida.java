package atelimatch.api.domain.medida;

import atelimatch.api.domain.pedido.Pedido;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medida {
    private Integer idMedida;
    private String categoria;
    private Integer valorMedida;

    @ManyToMany(mappedBy = "medidas")
    private Set<Pedido> pedidos = new HashSet<>();

    public Medida(DadosCadastroMedida dados) {
        this.categoria =dados.categoria();
        this.valorMedida = dados.valorMedida();
    }
}

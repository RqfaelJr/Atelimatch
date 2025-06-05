package atelimatch.api.domain.medida;

import atelimatch.api.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "Medida")
@Entity(name = "Medida")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idMedida")
public class Medida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedida;
    private String categoria;
    private Integer valorMedida;

    @ManyToMany(mappedBy = "medidas")
    private Set<Pedido> pedidos = new HashSet<>();

    public Medida(DadosCadastroMedida dados) {
        this.categoria =dados.categoria();
        this.valorMedida = dados.valorMedida();
    }

    public DadosDetalhamentoMedida atualizar(DadosAtualizacaoMedida dados) {
        if (dados.categoria() != null) this.categoria = dados.categoria();
        if (dados.valorMedida() != null) this.valorMedida = dados.valorMedida();

        return new DadosDetalhamentoMedida(this);
    }
}

package atelimatch.api.domain.grafico;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GraficoDTO {
    private List<String> labels;
    private List<Double> valores;
}

package atelimatch.api.domain.grafico;

import java.util.List;

public class ResultadoConsultaDTO {
    private List<String> colunas;
    private List<List<String>> dados;

    public ResultadoConsultaDTO(List<String> colunas, List<List<String>> dados) {
        this.colunas = colunas;
        this.dados = dados;
    }

    public List<String> getColunas() { return colunas; }
    public List<List<String>> getDados() { return dados; }
}


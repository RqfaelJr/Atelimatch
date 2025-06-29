package atelimatch.api.service.ia;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GroqAI {

    private String escapeForJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }

    public String consultaAI(String linguagemNatural) throws IOException, InterruptedException {
        var schema = SchemaLoader.carregarSchema("/db/schema.txt");
        String apiKey = "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";
        String apiUrl = "https://api.groq.com/openai/v1/chat/completions";

        String jsonPayload = getString(linguagemNatural, schema);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return extrairSqlDoJson(response.body());
    }

    private String getString(String linguagemNatural, String schema) {
        String promptText = "Considerando o schema do banco de dados " + escapeForJson(schema) +
                ", elabore uma consulta SQL que " + linguagemNatural + "é preciso fazer um inner join com Pessoa e selecionar tudo de pessoa também," +
                ". Na resposta apresente somente a consulta SQL sem qualquer formatação e sem o caractere ';'.";

        return String.format("""
        {
          "model": "meta-llama/llama-4-scout-17b-16e-instruct",
          "messages": [
            {"role": "system", "content": "Você é um assistente que gera consultas SQL."},
            {"role": "user", "content": "%s"}
          ],
          "max_tokens": 150,
          "temperature": 0.3
        }
        """, promptText.replace("\"", "\\\""));
    }

    private String extrairSqlDoJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        return root.path("choices").get(0).path("message").path("content").asText().trim();
    }
}

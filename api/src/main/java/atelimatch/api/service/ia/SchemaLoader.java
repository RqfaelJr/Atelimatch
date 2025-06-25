package atelimatch.api.service.ia;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class SchemaLoader {

    public static String carregarSchema(String caminhoArquivo) throws IOException {
        return Files.readString(Path.of(caminhoArquivo));
    }
}

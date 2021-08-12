// pacote
package petstore;

// bibliotecas


import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// classe
public class Pet {
    // atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereco da entidade Pet

    // metodos e funcoes
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // incluir - creat - post
    @Test // identifica o metodo ou funcao como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Entao
        // Given - When - Then

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

}
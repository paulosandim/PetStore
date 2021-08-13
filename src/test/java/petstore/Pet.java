// pacote
package petstore;

// bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

// classe
public class Pet {
    // atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereco da entidade Pet

    // metodos e funcoes
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // incluir - creat - post
    @Test(priority = 1) // identifica o metodo ou funcao como um teste para o TestNG
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
                .body("name", is("Narcejo"))
                .body("status", is("available"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("sta"))
        ;
    }

    @Test(priority = 2)
    public void consultarPet() {
        String petId = "156565692";

        String sta =
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Narcejo"))
                .body("category.name", is("dog"))
                .body("status", is("available"))
        .extract()
                .path("category.name")
        ;
        System.out.println("O token é " + sta);
    }
}

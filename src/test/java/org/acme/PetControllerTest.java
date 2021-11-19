package org.acme;

import com.example.petstore.pet.Pet;
import com.example.petstore.pet.PetData;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@QuarkusTest
public class PetControllerTest {

    @BeforeEach
    public void resetPets()
    {
        List<Pet> pets = new ArrayList<>()
        {{
            add( new Pet( 1, "dog", "boola", 5 ) );
            add( new Pet( 2, "cat", "sudda", 4 ) );
            add( new Pet( 3, "dog", "boola", 2 ) );
        }};

        List<Pet> pets1 = PetData.getInstance();
        pets1.clear();
        pets1.addAll( pets );
    }

    //1
	@Test
    public void testPetEndpoint() {
        given()
          .when().get("/pets")
          .then()
             .statusCode(200);
    }

    //2
    @Test
    void testPetEndpointSuccess()
    {
        given()
                .when().get( "/pets" )
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petId", notNullValue() )
                .body( "petAge", equalTo( new ArrayList()
                {{
                    add( 5 );
                    add( 4 );
                    add( 2 );
                }} ) )
                .body( "petName", equalTo( new ArrayList()
                {{
                    add( "boola" );
                    add( "sudda" );
                    add( "boola" );
                }} ) )
                .body( "petType", equalTo( new ArrayList()
                {{
                    add( "dog" );
                    add( "cat" );
                    add( "dog" );
                }} ) );

    }

    //3
    @Test
    public void testAddPetEndpoint()
    {
        given()
                .header( "Content-Type", "application/json" )
                .body( "{\n" +
                        "\t\"petId\":5,\n" +
                        "\t\"petType\":\"dog\",\n" +
                        "\t\"petName\":\"Timmy\",\n" +
                        "\t\"petAge\":5\n" +
                        "}" )
                .when().post( "/pets" )
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petAge", equalTo( 5 ) )
                .body( "petId", equalTo( 5 ) )
                .body( "petName", equalTo( "Timmy" ) )
                .body( "petType", equalTo( "dog" ) );

    }

    //4
    @Test
    void testDeletePet()
    {
        given()
                .header( "Content-Type", "application/json" )
                .pathParam( "petId", 1 )
                .when().delete( "/pets/{petId}" )
                .then()
                .assertThat()
                .statusCode( 200 );
    }

}
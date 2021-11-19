package org.acme;

import com.example.petstore.petType.PetType;
import com.example.petstore.petType.PetTypeData;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class PetTypeControllerTest {

    @BeforeEach
    public void resetPetTypes()
    {
        List<PetType> types = new ArrayList<>()
        {{
            add( new PetType( 1, "dog") );
            add( new PetType( 2, "cat") );
            add( new PetType( 3, "fish" ) );
        }};

        List<PetType> types1 = PetTypeData.getInstance();
        types1.clear();
        types1.addAll( types );
    }

    //1
    @Test
    public void testPetTypesEndpoint() {
        given()
                .when().get("/types")
                .then()
                .statusCode(200);
    }

    //2
    @Test
    void testPetTypeEndpointSuccess()
    {
        given()
                .when().get( "/types" )
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petTypeId", equalTo( new ArrayList()
                {{
                    add( 1 );
                    add( 2 );
                    add( 3 );
                }} ) )
                .body( "petType", equalTo( new ArrayList()
                {{
                    add( "dog" );
                    add( "cat" );
                    add( "fish" );
                }} ) );
    }

    //3
    @Test
    public void testAddPetTypeEndpoint()
    {
        given()
                .header( "Content-Type", "application/json" )
                .body( "{\n" +
                        "\t\"petTypeId\":5,\n" +
                        "\t\"petType\":\"bird\"\n" +
                        "}" )
                .when().post( "/types" )
                .then()
                .assertThat()
                .statusCode( 200 )
                .body( "petType", equalTo( "bird" ) )
                .body( "petTypeId", equalTo( 5 ) );
    }

    //4
    @Test
    void testDeletePetType()
    {
        given()
                .header( "Content-Type", "application/json" )
                .pathParam( "typeId", 1 )
                .when().delete( "/types/{typeId}" )
                .then()
                .assertThat()
                .statusCode( 200 );
    }
}

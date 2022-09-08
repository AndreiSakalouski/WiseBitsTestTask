import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class AbstractIntegrationTest {

    private static final String BASE_URL = "http://3.145.97.83:3333";

    @BeforeSuite
    public static void init() {
        RestAssured.baseURI = BASE_URL;
    }
}

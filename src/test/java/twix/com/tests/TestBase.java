package twix.com.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {
    static String BASE_URL = "https://github.com";


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }
}

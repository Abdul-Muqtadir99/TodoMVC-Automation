import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoMvcTest {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void shouldOpenTodoMvcReact() throws Exception {
        driver.get("https://todomvc.com/examples/react/dist/");
        assertEquals("TodoMVC: React", driver.getTitle());
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

}
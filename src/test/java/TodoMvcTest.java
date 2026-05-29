import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

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

    @Test
    void CanAddTodoItem() {
        driver.get("https://todomvc.com/examples/react/dist/");
        WebElement inputBox = driver.findElement(By.id("todo-input"));
        inputBox.click();
        inputBox.sendKeys("Buy Milk");
        inputBox.sendKeys(Keys.ENTER);

        WebElement TodoItem = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        assertEquals("Buy Milk", TodoItem.getText());
    }

 

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

}
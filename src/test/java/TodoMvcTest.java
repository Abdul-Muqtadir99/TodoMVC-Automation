import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoMvcTest {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void shouldOpenTodoMvcReact() throws Exception {
        driver.navigate();
        assertEquals("TodoMVC: React", driver.getTitle());
    }

    @Test
    void CanAddTodoItem() {
        driver.navigate();
        WebElement inputBox = driver.findElement(By.id("todo-input"));
        inputBox.click();
        inputBox.sendKeys("Buy Milk");
        inputBox.sendKeys(Keys.ENTER);

        WebElement TodoItem = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        assertEquals("Buy Milk", TodoItem.getText());
    }

    @Test
    void CanAddMultipleTodoItem() {
        driver.navigate();
        WebElement inputBox = driver.findElement(By.id("todo-input"));
        inputBox.click();
        inputBox.sendKeys("Buy Milk");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Bread");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Eggs");
        inputBox.sendKeys(Keys.ENTER);

        List<WebElement> TodoItems = driver.findElements(By.cssSelector(".todo-list li"));
        assertEquals(3, TodoItems.size());
    }

    @Test
    void CanMarkAllTodoItemsAsComplete() {
        driver.navigate();
        WebElement inputBox = driver.findElement(By.id("todo-input"));
        inputBox.click();
        inputBox.sendKeys("Buy Milk");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Bread");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Eggs");
        inputBox.sendKeys(Keys.ENTER);

        WebElement ToggleAllButton = driver.findElement(By.id("toggle-all"));
        ToggleAllButton.click();

        List<WebElement> TodoCheckboxes = driver.findElements(
                By.cssSelector("[data-testid='todo-item-toggle']"));
        assertEquals(3, TodoCheckboxes.size());

        for (WebElement checkbox : TodoCheckboxes) {
            assertTrue(checkbox.isSelected());
        }
    }

    @Test
    void CanMarkAllTodoItemsAsIncomplete() throws InterruptedException {
        driver.navigate();
        WebElement inputBox = driver.findElement(By.id("todo-input"));
        inputBox.click();
        inputBox.sendKeys("Buy Milk");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Bread");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Eggs");
        inputBox.sendKeys(Keys.ENTER);

        WebElement ToggleAllButton = driver.findElement(By.id("toggle-all"));
        ToggleAllButton.click();
        Thread.sleep(1000);
        ToggleAllButton.click();

        List<WebElement> TodoCheckboxes = driver.findElements(
                By.cssSelector("[data-testid='todo-item-toggle']"));
        assertEquals(3,TodoCheckboxes.size());

        for (WebElement checkbox : TodoCheckboxes) {
            assertFalse(checkbox.isSelected());
        }
    }

    @Test
    void CanClearAllCompletedTodoItems() {
        driver.navigate();
        WebElement inputBox = driver.findElement(By.id("todo-input"));
        inputBox.click();
        inputBox.sendKeys("Buy Milk");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Bread");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.sendKeys("Buy Eggs");
        inputBox.sendKeys(Keys.ENTER);

        WebElement ToggleAllButton = driver.findElement(By.id("toggle-all"));
        ToggleAllButton.click();

        WebElement ClearAllButton = driver.findElement(By.cssSelector(".clear-completed"));
        ClearAllButton.click();

        List<WebElement> TodoCheckboxes = driver.findElements(
                By.cssSelector("[data-testid='todo-item-toggle']"));
        assertEquals(0,TodoCheckboxes.size());
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

}
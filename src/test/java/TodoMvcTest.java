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
        TodoMvcPOM reactPage = new TodoMvcPOM(driver);
        reactPage.navigate();
        assertEquals("TodoMVC: React", driver.getTitle());
    }

    @Test
    void CanAddTodoItem() {
        TodoMvcPOM reactPage = new TodoMvcPOM(driver);
        reactPage.navigate();
        reactPage.addItem("Buy Milk");

        WebElement TodoItem = driver.findElement(By.cssSelector("[data-testid='todo-item-label']"));
        assertEquals("Buy Milk", TodoItem.getText());
    }

    @Test
    void CanAddMultipleTodoItem() {
        TodoMvcPOM reactPage = new TodoMvcPOM(driver);
        reactPage.navigate();
        reactPage.addItem("Buy Milk");
        reactPage.addItem("Buy Bread");
        reactPage.addItem("Buy Eggs");
        List<WebElement> TodoItems = driver.findElements(By.cssSelector(".todo-list li"));
        assertEquals(3, TodoItems.size());
    }

    @Test
    void CanMarkAllTodoItemsAsComplete() {
        TodoMvcPOM reactPage = new TodoMvcPOM(driver);
        reactPage.navigate();
        reactPage.addItem("Buy Milk");
        reactPage.addItem("Buy Bread");
        reactPage.addItem("Buy Eggs");
        reactPage.ToggleAll();
        List<WebElement> TodoCheckboxes = reactPage.getTodoCheckboxes();

        assertEquals(3, TodoCheckboxes.size());

        for (WebElement checkbox : TodoCheckboxes) {
            assertTrue(checkbox.isSelected());
        }
    }

    @Test
    void CanMarkAllTodoItemsAsIncomplete() throws InterruptedException {
        TodoMvcPOM reactPage = new TodoMvcPOM(driver);
        reactPage.navigate();
        reactPage.addItem("Buy Milk");
        reactPage.addItem("Buy Bread");
        reactPage.addItem("Buy Eggs");
        reactPage.ToggleAll();
        reactPage.ToggleAll();
        List<WebElement> TodoCheckboxes = reactPage.getTodoCheckboxes();

        assertEquals(3, TodoCheckboxes.size());

        for (WebElement checkbox : TodoCheckboxes) {
            assertFalse(checkbox.isSelected());
        }
    }
    @Test
    void CanClearAllCompletedTodoItems() {
        TodoMvcPOM reactPage = new TodoMvcPOM(driver);
        reactPage.navigate();
        reactPage.addItem("Buy Milk");
        reactPage.addItem("Buy Bread");
        reactPage.addItem("Buy Eggs");
        reactPage.ToggleAll();
        reactPage.ClearAllCompleted();

        List<WebElement> TodoCheckboxes = reactPage.getTodoCheckboxes();

        assertEquals(0, TodoCheckboxes.size());
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();

    }
}
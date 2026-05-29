import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoMvcPOM {
    public WebDriver driver;

    private By Checkboxes = By.cssSelector("[data-testid='todo-item-toggle']");
    private By ToggleAll = By.id("toggle-all");

    public TodoMvcPOM(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/react/dist/");
    }

    public void addItem(String TodoItem) {
        WebElement addTodoItem = driver.findElement(By.id("todo-input"));
        addTodoItem.click();
        addTodoItem.sendKeys(TodoItem);
        addTodoItem.sendKeys(Keys.ENTER);
    }

    public void ToggleAll(){
        WebElement ToggleAllButton = driver.findElement(ToggleAll);
        ToggleAllButton.click();
    }

    public void ClearAllCompleted(){
        WebElement ClearAllButton = driver.findElement(By.cssSelector(".clear-completed"));
        ClearAllButton.click();
    }

    public List<WebElement> getTodoCheckboxes() {
        return driver.findElements(Checkboxes);
    }


}

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.BaseTest;
import ru.praktikum.MainPage;

import static org.junit.Assert.assertEquals;

/**
 * Тесты для проверки навигации в конструкторе.
 */
public class ConstructorTest extends BaseTest {
    private MainPage homePage;

    @Override
    public void initialize() {
        super.initialize();
        homePage = new MainPage(browser);
    }

    @Test
    @DisplayName("Переход в раздел 'Булки'")
    public void testBunsTabNavigation() {
        homePage.selectSaucesTab();
        homePage.selectBunsTab();
        assertEquals("Булки", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    public void testSaucesTabNavigation() {
        homePage.selectSaucesTab();
        assertEquals("Соусы", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    public void testFillingsTabNavigation() {
        homePage.selectFillingsTab();
        assertEquals("Начинки", homePage.getActiveTabText());
    }
}
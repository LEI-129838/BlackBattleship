package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit para validar o cenário UserStoryTest4 (US09).
 * Utiliza o Page Object {@link UserStory4} para testar a consulta do histórico de partidas.
 */
public class UserStory4Test {

    private WebDriver driver;
    private UserStory4 userStory4;

    /**
     * Configura o WebDriver Chrome e inicializa o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        userStory4 = new UserStory4(driver);
    }

    /**
     * Executa a navegação até ao histórico de partidas e valida a exibição da tabela de resultados.
     */
    @Test
    public void testViewMatchHistory() {
        userStory4.openGamePage();
        userStory4.navigateToMatchHistory();
        userStory4.inspectHistoryTable();

        assertTrue(userStory4.isMatchHistoryDisplayed(),
                "A tabela com o histórico de partidas deveria estar visível na página History.");
    }

    /**
     * Encerra a sessão do navegador após a execução do teste.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
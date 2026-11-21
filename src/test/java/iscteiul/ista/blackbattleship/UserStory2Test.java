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
 * Classe de testes JUnit para validar o cenário UserStoryTest2 (US02).
 * Utiliza o Page Object {@link UserStory2} para testar a consulta das regras do jogo.
 */
public class UserStory2Test {

    private WebDriver driver;
    private UserStory2 userStory2;

    /**
     * Configura o WebDriver Chrome e inicializa o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        userStory2 = new UserStory2(driver);
    }

    /**
     * Executa a navegação até à secção de regras do jogo Battleship e valida a sua apresentação.
     */
    @Test
    public void testReadGameRules() {
        userStory2.openGamePage();
        userStory2.viewRulesSection();

        assertTrue(userStory2.areRulesDisplayed(),
                "A secção com as regras e instruções do jogo deveria estar visível.");
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
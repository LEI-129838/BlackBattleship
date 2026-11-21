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
 * Classe de testes JUnit para validar o cenário UserStoryTest3 (US05).
 * Utiliza o Page Object {@link UserStory3} para testar o início de uma partida contra o robot.
 */
public class UserStory3Test {

    private WebDriver driver;
    private UserStory3 userStory3;

    /**
     * Configura o WebDriver Chrome e inicializa o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        userStory3 = new UserStory3(driver);
    }

    /**
     * Executa o fluxo de início de partida contra um robot e valida o carregamento do jogo.
     */
    @Test
    public void testPlayAgainstRobot() {
        userStory3.openGamePage();
        userStory3.clickPlayVsRobot();

        assertTrue(userStory3.isRobotMatchInitiated(),
                "A partida contra o robot deveria ter sido iniciada na página do Battleship.");
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
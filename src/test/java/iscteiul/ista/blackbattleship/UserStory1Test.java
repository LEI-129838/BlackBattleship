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
 * Classe de testes JUnit para validar o cenário UserStoryTest1 (US01).
 * Utiliza o Page Object {@link UserStory1} para testar a definição do nickname.
 */
public class UserStory1Test {

    private WebDriver driver;
    private UserStory1 userStory1;

    /**
     * Configura o WebDriver Chrome e inicializa o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        userStory1 = new UserStory1(driver);
    }

    /**
     * Executa o fluxo completo de definição e confirmação do nickname na página do jogo.
     */
    @Test
    public void testSetNickname() {
        userStory1.openGamePage();
        userStory1.closeCookiePopup();
        userStory1.clickStartButton();
        userStory1.enterAndConfirmNickname("olá");

        assertTrue(userStory1.isNicknameSubmitted(),
                "O utilizador deveria permanecer na sessão do jogo após confirmar o nickname.");
    }

    /**
     * Encerra a sessão do navegador após a execução de cada teste.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

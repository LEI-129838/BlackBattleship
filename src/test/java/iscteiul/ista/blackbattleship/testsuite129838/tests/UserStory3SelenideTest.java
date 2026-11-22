package iscteiul.ista.blackbattleship.testsuite129838.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.testsuite129838.pages.UserStory3Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit com Selenide para validar o cenário UserStoryTest3 (US05).
 */
public class UserStory3SelenideTest {

    private UserStory3Page userStory3Page;

    /**
     * Configura o tamanho da janela do Selenide e regista o listener do Allure Report.
     */
    @BeforeAll
    public static void globalSetUp() {
        Configuration.browserSize = "1425x794";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    /**
     * Abre a página do jogo Battleship e inicializa o Page Object antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        userStory3Page = open("https://papergames.io/en/battleship", UserStory3Page.class);
        sleep(1200);
    }

    /**
     * Testa o início de uma partida contra o robot utilizando Selenide.
     */
    @Test
    @Description("US05 - Iniciar partida contra um robot em Battleship com Selenide")
    public void testPlayAgainstRobotSelenide() {
        userStory3Page.closeCookiePopupIfPresent();
        userStory3Page.clickPlayVsRobot();

        assertTrue(userStory3Page.isRobotMatchInitiated(),
                "A partida contra o robot deveria ter sido iniciada na sala do Battleship.");
    }

    /**
     * Fecha o navegador após a execução de cada teste.
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
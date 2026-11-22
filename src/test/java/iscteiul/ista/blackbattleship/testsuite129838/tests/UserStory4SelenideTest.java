package iscteiul.ista.blackbattleship.testsuite129838.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.testsuite129838.pages.UserStory4Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit com Selenide para validar o cenário UserStoryTest4 (US09).
 */
public class UserStory4SelenideTest {

    private UserStory4Page userStory4Page;

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
        userStory4Page = open("https://papergames.io/en/battleship", UserStory4Page.class);
        sleep(1200);
    }

    /**
     * Testa a consulta da página de histórico de partidas utilizando Selenide.
     */
    @Test
    @Description("US09 - Consultar histórico de partidas de Battleship com Selenide")
    public void testViewMatchHistorySelenide() {
        userStory4Page.closeCookiePopupIfPresent();
        userStory4Page.navigateToMatchHistory();
        userStory4Page.inspectHistoryTable();

        assertTrue(userStory4Page.isMatchHistoryDisplayed(),
                "A página de histórico de partidas deveria estar visível no navegador.");
    }

    /**
     * Fecha o navegador após a execução de cada teste.
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
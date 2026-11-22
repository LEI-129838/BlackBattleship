package iscteiul.ista.blackbattleship.testsuite129838.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.testsuite129838.pages.UserStory2Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit com Selenide para validar o cenário UserStoryTest2 (US02).
 */
public class UserStory2SelenideTest {

    private UserStory2Page userStory2Page;

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
        userStory2Page = open("https://papergames.io/en/battleship", UserStory2Page.class);
        sleep(1200);
    }

    /**
     * Testa a navegação e consulta das regras do jogo Battleship utilizando Selenide.
     */
    @Test
    @Description("US02 - Consultar regras e guia do jogo Battleship com Selenide")
    public void testReadGameRulesSelenide() {
        userStory2Page.closeCookiePopupIfPresent();
        userStory2Page.viewRulesSection();

        assertTrue(userStory2Page.areRulesDisplayed(),
                "A secção com as regras e instruções do jogo deveria estar visível.");
    }

    /**
     * Fecha o navegador após a execução de cada teste.
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
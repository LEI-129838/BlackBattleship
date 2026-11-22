package iscteiul.ista.blackbattleship.testsuite129838.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.testsuite129838.pages.UserStory1Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit com Selenide para validar o cenário UserStoryTest1 (US01).
 */
public class UserStory1SelenideTest {

    private UserStory1Page userStory1Page;

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
        userStory1Page = open("https://papergames.io/en/battleship", UserStory1Page.class);
        sleep(1200);
    }

    /**
     * Testa a definição e confirmação do nickname do jogador utilizando Selenide.
     */
    @Test
    @Description("US01 - Definir nickname na página do jogo Battleship com Selenide")
    public void testSetNicknameSelenide() {
        userStory1Page.closeCookiePopupIfPresent();
        userStory1Page.clickStartButton();
        userStory1Page.enterAndConfirmNickname("olá");

        assertTrue(userStory1Page.isNicknameSubmitted(),
                "O utilizador deveria permanecer na sessão do jogo após confirmar o nickname.");
    }

    /**
     * Fecha o navegador após a execução de cada teste.
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
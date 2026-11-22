package iscteiul.ista.blackbattleship.testsuite129838.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Page Object Class em Selenide para o cenário UserStoryTest4 (US09 - Consultar histórico de partidas).
 * Encapsula os localizadores e as interações para aceder e visualizar o histórico em papergames.io.
 */
public class UserStory4Page {

    /**
     * Botão opcional para rejeitar o banner de consentimento de cookies.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private SelenideElement cookieConsentButton;

    /**
     * Ligação de navegação para a página de histórico de partidas (History).
     */
    @FindBy(linkText = "History")
    private SelenideElement historyLink;

    /**
     * Conteúdo da tabela de dados que lista o histórico de partidas jogadas.
     */
    @FindBy(css = ".mdc-data-table__content")
    private SelenideElement historyTableContent;

    /**
     * Contentor principal da página de histórico de partidas.
     */
    @FindBy(css = "main")
    private SelenideElement mainContent;

    /**
     * Fecha o pop-up de consentimento de cookies caso esteja visível no ecrã.
     */
    public void closeCookiePopupIfPresent() {
        try {
            cookieConsentButton.shouldBe(clickable, Duration.ofSeconds(4)).click();
            sleep(800);
        } catch (Throwable ignored) {
            // Ignora caso o banner de cookies não apareça nesta sessão
        }
    }

    /**
     * Navega para a página de histórico de partidas através da ligação History ou do URL direto.
     */
    public void navigateToMatchHistory() {
        try {
            historyLink.shouldBe(clickable, Duration.ofSeconds(3)).click();
        } catch (Throwable ignored) {
            open("https://papergames.io/en/match-history?gameType=Battleships");
        }
        sleep(1200);
    }

    /**
     * Aguarda pelo carregamento da área de histórico de partidas e interage com a tabela caso existam registos.
     */
    public void inspectHistoryTable() {
        mainContent.shouldBe(visible, Duration.ofSeconds(10));
        if (historyTableContent.exists() && historyTableContent.isDisplayed()) {
            historyTableContent.click();
        }
        sleep(1200);
    }

    /**
     * Verifica se a página do histórico de partidas está aberta e visível no navegador.
     *
     * @return {@code true} se o URL corresponder ao histórico e o conteúdo principal estiver visível
     */
    public boolean isMatchHistoryDisplayed() {
        return url().toLowerCase().contains("match-history") && mainContent.isDisplayed();
    }
}
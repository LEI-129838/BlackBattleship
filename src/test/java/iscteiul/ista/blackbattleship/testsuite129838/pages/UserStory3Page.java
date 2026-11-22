package iscteiul.ista.blackbattleship.testsuite129838.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Page Object Class em Selenide para o cenário UserStoryTest3 (US05 - Jogar contra um robot).
 * Encapsula os localizadores CSS e as interações para iniciar uma partida a solo em papergames.io.
 */
public class UserStory3Page {

    /**
     * Botão opcional para rejeitar o banner de consentimento de cookies.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private SelenideElement cookieConsentButton;

    /**
     * Botão na página do Battleship para iniciar uma partida contra o robot.
     */
    @FindBy(css = ".w-100:nth-child(2) > .btn .flex-grow-1")
    private SelenideElement playVsRobotButton;

    /**
     * Campo opcional de nickname caso a plataforma solicite identificação antes de entrar na sala.
     */
    @FindBy(css = ".input-xl")
    private SelenideElement guestNicknameInput;

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
     * Clica no botão para iniciar uma partida contra o robot e preenche o nickname caso seja pedido.
     */
    public void clickPlayVsRobot() {
        playVsRobotButton.shouldBe(clickable, Duration.ofSeconds(10)).click();
        sleep(1200);
        completeGuestPromptIfPresent();
    }

    /**
     * Preenche um nickname de convidado caso a modal de identificação surja após clicar no botão.
     */
    private void completeGuestPromptIfPresent() {
        try {
            guestNicknameInput.shouldBe(interactable, Duration.ofSeconds(8)).click();
            sleep(500);
            guestNicknameInput.sendKeys("Jogador129838");
            sleep(1000);
            guestNicknameInput.pressEnter();
            sleep(2500);
        } catch (Throwable ignored) {
            // Ignora se entrar diretamente na sala sem solicitar nickname
        }
    }

    /**
     * Verifica se a sessão avançou para a sala ou tabuleiro da partida contra o robot.
     *
     * @return {@code true} se a partida contra o robot tiver sido iniciada
     */
    public boolean isRobotMatchInitiated() {
        String currentUrl = url();
        boolean urlChanged = currentUrl.contains("/r/") || !currentUrl.endsWith("/battleship");
        boolean lobbyButtonHidden = !playVsRobotButton.exists() || !playVsRobotButton.isDisplayed();
        return currentUrl.contains("papergames.io") && (urlChanged || lobbyButtonHidden);
    }
}
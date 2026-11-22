package iscteiul.ista.blackbattleship.testsuite129838.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Page Object Class em Selenide para o cenário UserStoryTest1 (US01 - Definir nickname).
 * Utiliza Page Factory com {@link SelenideElement} para encapsular os elementos da página.
 */
public class UserStory1Page {

    /**
     * Botão para rejeitar o banner de consentimento de cookies.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private SelenideElement cookieConsentButton;

    /**
     * Botão principal para iniciar e abrir o ecrã de definição de nickname.
     */
    @FindBy(css = ".w-100:nth-child(2) > .btn .flex-grow-1")
    private SelenideElement startNicknameButton;

    /**
     * Campo de texto onde o jogador introduz o seu nickname.
     */
    @FindBy(css = ".input-xl")
    private SelenideElement nicknameInput;

    /**
     * Aguarda brevemente e fecha o pop-up de consentimento de cookies caso apareça no ecrã.
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
     * Clica no botão para avançar para a introdução do nickname.
     */
    public void clickStartButton() {
        startNicknameButton.shouldBe(clickable, Duration.ofSeconds(10)).click();
        sleep(1200);
    }

    /**
     * Preenche o campo de nickname e confirma pressionando ENTER.
     *
     * @param nickname nome de utilizador a definir
     */
    public void enterAndConfirmNickname(String nickname) {
        nicknameInput.shouldBe(interactable, Duration.ofSeconds(10)).click();
        sleep(500);
        nicknameInput.sendKeys(nickname);
        sleep(1000);
        nicknameInput.pressEnter();
        sleep(1500);
    }

    /**
     * Verifica se a sessão permanece ativa no domínio do jogo após submeter o nickname.
     *
     * @return {@code true} se o URL atual pertencer a {@code papergames.io}
     */
    public boolean isNicknameSubmitted() {
        return url().contains("papergames.io");
    }
}
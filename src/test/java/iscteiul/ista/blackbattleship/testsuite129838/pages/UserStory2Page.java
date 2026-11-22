package iscteiul.ista.blackbattleship.testsuite129838.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Page Object Class em Selenide para o cenário UserStoryTest2 (US02 - Consultar regras e guia).
 * Encapsula os localizadores CSS e as interações com a secção de regras do jogo Battleship.
 */
public class UserStory2Page {

    /**
     * Botão opcional para rejeitar o banner de consentimento de cookies.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private SelenideElement cookieConsentButton;

    /**
     * Secção da página que contém as regras e instruções do jogo Battleship.
     */
    @FindBy(css = ".section .col-12")
    private SelenideElement rulesSection;

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
     * Desloca a página até à secção de regras e interage com a área de instruções.
     */
    public void viewRulesSection() {
        rulesSection.shouldBe(visible, Duration.ofSeconds(10))
                .scrollIntoView("{block: 'center'}");
        sleep(1000);
        rulesSection.shouldBe(clickable, Duration.ofSeconds(5)).click();
        sleep(1200);
    }

    /**
     * Verifica se a secção de regras está visível e contém texto descritivo.
     *
     * @return {@code true} se a secção de regras estiver visível e preenchida
     */
    public boolean areRulesDisplayed() {
        return rulesSection.isDisplayed() && !rulesSection.getText().trim().isEmpty();
    }
}
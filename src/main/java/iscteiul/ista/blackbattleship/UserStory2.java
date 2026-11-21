package iscteiul.ista.blackbattleship;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class correspondente ao cenário UserStoryTest2 (US02 - Consultar regras e guia).
 * Encapsula os localizadores CSS e as interações com a secção de regras do jogo Battleship.
 */
public class UserStory2 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Botão opcional para rejeitar o banner de cookies caso apareça sobre a página.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private WebElement cookieConsentButton;

    /**
     * Secção da página que contém as regras e instruções do jogo Battleship.
     */
    @FindBy(css = ".section .col-12")
    private WebElement rulesSection;

    /**
     * Construtor da classe Page Object UserStory2.
     *
     * @param driver instância ativa do {@link WebDriver}
     */
    public UserStory2(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Abre a página do jogo Battleship e define a dimensão da janela do navegador.
     */
    public void openGamePage() {
        driver.get("https://papergames.io/en/battleship");
        driver.manage().window().setSize(new Dimension(1425, 794));
        humanPause(1500);
        closeCookiePopupIfPresent();
    }

    /**
     * Fecha o pop-up de consentimento de cookies se estiver visível.
     */
    private void closeCookiePopupIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton)).click();
            humanPause(800);
        } catch (Exception ignored) {
            // Ignora se o banner não estiver presente
        }
    }

    /**
     * Desloca a página até à secção de regras e clica na área de instruções do jogo.
     */
    public void viewRulesSection() {
        wait.until(ExpectedConditions.visibilityOf(rulesSection));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", rulesSection);
        humanPause(1000);
        wait.until(ExpectedConditions.elementToBeClickable(rulesSection)).click();
        humanPause(1500);
    }

    /**
     * Verifica se a secção de regras está visível e contém texto descritivo.
     *
     * @return {@code true} se a secção de regras estiver visível e não estiver vazia
     */
    public boolean areRulesDisplayed() {
        return rulesSection.isDisplayed() && !rulesSection.getText().trim().isEmpty();
    }

    /**
     * Pausa a execução para simular o tempo de leitura e interação humana na GUI.
     *
     * @param millis tempo de espera em milissegundos
     */
    private void humanPause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
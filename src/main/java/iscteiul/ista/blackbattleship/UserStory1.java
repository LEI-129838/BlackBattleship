package iscteiul.ista.blackbattleship;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class correspondente ao cenário UserStoryTest1 (US01 - Definir nickname).
 * Encapsula os localizadores CSS e as interações na página do jogo Battleship em papergames.io.
 */
public class UserStory1 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Botão para rejeitar/fechar o pop-up de consentimento de cookies.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private WebElement cookieConsentButton;

    /**
     * Botão principal para iniciar e abrir o ecrã de definição de nickname.
     */
    @FindBy(css = ".w-100:nth-child(2) > .btn .flex-grow-1")
    private WebElement startNicknameButton;

    /**
     * Campo de texto onde o jogador introduz o seu nickname.
     */
    @FindBy(css = ".input-xl")
    private WebElement nicknameInput;

    /**
     * Construtor da classe Page Object UserStory1.
     * Inicializa os elementos da página com PageFactory e configura a espera explícita.
     *
     * @param driver instância ativa do {@link WebDriver}
     */
    public UserStory1(WebDriver driver) {
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
    }

    /**
     * Fecha o pop-up de consentimento de cookies caso esteja visível no ecrã.
     */
    public void closeCookiePopup() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton)).click();
            humanPause(1000);
        } catch (Exception ignored) {
            // Ignora caso o banner de cookies não apareça nesta sessão
        }
    }

    /**
     * Clica no botão para iniciar e avançar para a definição do nickname.
     */
    public void clickStartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(startNicknameButton)).click();
        humanPause(1500);
    }

    /**
     * Introduz o nickname no campo de texto e confirma pressionando a tecla ENTER.
     *
     * @param nickname nome de utilizador a definir para a sessão de jogo
     */
    public void enterAndConfirmNickname(String nickname) {
        wait.until(ExpectedConditions.visibilityOf(nicknameInput));
        nicknameInput.clear();
        nicknameInput.sendKeys(nickname);
        humanPause(1000);
        nicknameInput.sendKeys(Keys.ENTER);
        humanPause(2000);
    }

    /**
     * Verifica se o fluxo de definição de nickname avançou com sucesso.
     *
     * @return {@code true} se a página atual pertence ao domínio do jogo Battleship
     */
    public boolean isNicknameSubmitted() {
        return driver.getCurrentUrl().contains("papergames.io");
    }

    /**
     * Pausa a execução durante um intervalo definido para simular a interação humana na GUI.
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

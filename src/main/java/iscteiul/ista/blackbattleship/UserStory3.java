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
 * Page Object Class correspondente ao cenário UserStoryTest3 (US05 - Jogar contra um robot).
 * Encapsula os localizadores CSS e as interações para iniciar uma partida a solo em papergames.io.
 */
public class UserStory3 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Botão opcional para fechar o banner de consentimento de cookies.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private WebElement cookieConsentButton;

    /**
     * Botão na página do Battleship para iniciar uma partida contra o robot.
     */
    @FindBy(css = ".w-100:nth-child(2) > .btn .flex-grow-1")
    private WebElement playVsRobotButton;

    /**
     * Campo opcional de nickname caso a plataforma solicite identificação antes de entrar no tabuleiro.
     */
    @FindBy(css = ".input-xl")
    private WebElement guestNicknameInput;

    /**
     * Construtor da classe Page Object UserStory3.
     *
     * @param driver instância ativa do {@link WebDriver}
     */
    public UserStory3(WebDriver driver) {
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
     * Fecha o pop-up de consentimento de cookies se estiver visível no ecrã.
     */
    private void closeCookiePopupIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton)).click();
            humanPause(800);
        } catch (Exception ignored) {
            // Ignora caso o banner de cookies não apareça nesta sessão
        }
    }

    /**
     * Clica no botão para iniciar uma partida contra o robot e conclui a entrada na sala de jogo.
     */
    public void clickPlayVsRobot() {
        wait.until(ExpectedConditions.elementToBeClickable(playVsRobotButton)).click();
        humanPause(1500);
        completeGuestPromptIfPresent();
    }

    /**
     * Preenche um nickname de convidado caso a modal de identificação surja após clicar no botão.
     */
    private void completeGuestPromptIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement input = shortWait.until(ExpectedConditions.visibilityOf(guestNicknameInput));
            input.clear();
            input.sendKeys("Jogador129838");
            humanPause(800);
            input.sendKeys(Keys.ENTER);
            humanPause(2000);
        } catch (Exception ignored) {
            // Ignora se entrar diretamente na partida sem pedir nickname
        }
    }

    /**
     * Verifica se a sessão avançou para a sala de partida contra o robot em papergames.io.
     *
     * @return {@code true} se o navegador estiver numa sala de jogo ativa
     */
    public boolean isRobotMatchInitiated() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("papergames.io")
                && (currentUrl.contains("/r/") || !currentUrl.endsWith("/battleship"));
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
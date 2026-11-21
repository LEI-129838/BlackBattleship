package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Class correspondente ao cenário UserStoryTest4 (US09 - Consultar histórico de partidas).
 * Encapsula os localizadores e as interações para aceder e visualizar o histórico de partidas em papergames.io.
 */
public class UserStory4 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Botão opcional para rejeitar o banner de consentimento de cookies.
     */
    @FindBy(css = ".fc-cta-do-not-consent > .fc-button-label")
    private WebElement cookieConsentButton;

    /**
     * Ligação de navegação para a página de histórico de partidas (History).
     */
    @FindBy(linkText = "History")
    private WebElement historyLink;

    /**
     * Conteúdo da tabela de dados que lista o histórico de partidas jogadas.
     */
    @FindBy(css = ".mdc-data-table__content")
    private WebElement historyTableContent;

    /**
     * Contentor principal da página de histórico de partidas.
     */
    @FindBy(css = "main")
    private WebElement mainContent;

    /**
     * Construtor da classe Page Object UserStory4.
     *
     * @param driver instância ativa do {@link WebDriver}
     */
    public UserStory4(WebDriver driver) {
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
     * Navega para a página de histórico de partidas através da ligação History ou do URL de Battleships.
     */
    public void navigateToMatchHistory() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.elementToBeClickable(historyLink)).click();
        } catch (Exception e) {
            driver.get("https://papergames.io/en/match-history?gameType=Battleships");
        }
        humanPause(1500);
    }

    /**
     * Aguarda pelo carregamento da área de histórico de partidas e interage com a tabela caso existam registos.
     */
    public void inspectHistoryTable() {
        wait.until(ExpectedConditions.visibilityOf(mainContent));
        List<WebElement> tables = driver.findElements(By.cssSelector(".mdc-data-table__content"));
        if (!tables.isEmpty() && tables.get(0).isDisplayed()) {
            historyTableContent.click();
        }
        humanPause(1500);
    }

    /**
     * Verifica se a página do histórico de partidas está aberta e carregada no navegador.
     *
     * @return {@code true} se o URL corresponder ao histórico de partidas e o conteúdo principal estiver visível
     */
    public boolean isMatchHistoryDisplayed() {
        return driver.getCurrentUrl().toLowerCase().contains("match-history")
                && mainContent.isDisplayed();
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
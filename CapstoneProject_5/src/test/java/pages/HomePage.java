package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    // ===== DOWNLOAD SECTION =====

    By downloadMenu = By.xpath("//a[@href='/download/']");
    By getWordpress = By.xpath("//a[contains(text(),'Get WordPress')]");

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickDownloadAndGetWordPress() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadMenu)).click();
        wait.until(ExpectedConditions.urlContains("download"));
        wait.until(ExpectedConditions.elementToBeClickable(getWordpress)).click();
    }

    public String getMiddleText() {
        return driver.findElement(By.tagName("h1")).getText();
    }

    // ===== COMMUNITY SECTION =====

    By communityMenu = By.xpath("//*[@id=\"modal-1-content\"]/ul/li[6]/button/span");
    By photoDirectory = By.xpath("//*[@id=\"modal-1-content\"]/ul/li[6]/ul/li[3]/a/span");
    By searchBox = By.xpath("//*[@id=\"wp-block-search__input-8\"]");
    By images = By.xpath("//*[@id=\"wp--skip-link--target\"]/div/div[1]/ul");

    public void clickCommunityAndPhotoDirectory() {

        driver.get("https://wordpress.org/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(communityMenu));

        driver.findElement(communityMenu).click();

        wait.until(ExpectedConditions.elementToBeClickable(photoDirectory)).click();

        wait.until(ExpectedConditions.urlContains("photos"));
    }

    public void searchFor(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox))
                .sendKeys(text + "\n");
    }

    public boolean areImagesDisplayed() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(images));

        List<WebElement> imgList = driver.findElements(images);

        return imgList.size() > 0;
    }
}
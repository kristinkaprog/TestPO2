package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage {

    public WebDriver driver;

    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(xpath = "//*[@id=\"FilterBrands\"]/h5/strong/a")
    private WebElement Brand;

    @FindBy(xpath = "//*[@id=\"filterItem_14\"]/h5/strong/a")
    private WebElement Season;



    @FindBy(xpath = "//*[@id=\"BrandsWrapper\"]/div[1]/label")
    private WebElement Jcrew;

    @FindBy(xpath = "//*[@id=\"Property14Wrapper\"]/div[2]/label")
    private WebElement Summer;


    @FindBy(xpath = "//*[@id=\"CatalogueSection\"]/section/div[2]/div/div/div[2]/h5[1]")
    private WebElement IdElement;

    /*@FindBy(xpath = "//*[@id=\"wrapper\"]/div[2]/section/div/div[1]/div/div/div[12]/button")
    private WebElement clearBtn;

     */

    // Используйте @CacheLookup, чтобы избежать повторного поиска элемента при каждом вызове метода
    @FindBy(xpath = "// * [@id=\"wrapper\"]/div[2]/section/div/div[1]/div/div/div[12]/button")
    @CacheLookup
    private WebElement clearBtn;

    @FindBy(xpath = "//*[@id=\"navbarDropdownMenuLink-4\"]/div[2]")
    private WebElement FirstStep;




    @FindBy(xpath = "//*[@id=\"SortOrder\"]")
    private WebElement Sort;


    @FindBy(xpath = "//*[@id=\"SortOrder\"]/option[4]")
    private WebElement RichSort;

    @FindBy(xpath = "/html/body/header/nav[2]/div/div[2]/ul/li/a")
    private WebElement SecStep;

    public void ChooseRich(){
        RichSort.click();
    }


    public void OpenSort(){
        Sort.click();
    }



    public String getUserName() {
        String id = IdElement.getText();
        return id; }

    public void openBrend(){
        Brand.click();
    }

    public void openSeason(){
        Season.click();
    }

    public void chooseJcrew(){
        Jcrew.click();
    }

    public void chooseSummer(){
        Summer.click();
    }

    /*public void ClearFilter(){
        clearBtn.click();
    }

     */
    public void clearFilter() {
        // Используйте WebDriverWait для ожидания кликабельности элемента
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(clearBtn)).click();
    }

    public void LogOut(){
        FirstStep.click();
        SecStep.click();
    }






}

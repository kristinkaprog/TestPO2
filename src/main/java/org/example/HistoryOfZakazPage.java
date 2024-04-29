package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class HistoryOfZakazPage {
    //*[@id="navbarDropdownMenuLink-20"]
    private static final String CABINET_MENU = "//*[@id='navbarDropdownMenuLink-20']";
    //*[@id="navbarCollapse"]/ul[2]/li[5]/div/a[16]
    private static final String ORDERS_HISTORY = "//*[@id='navbarCollapse']/ul[2]/li[5]/div/a[16]";

    private static WebElement parentElement;
    int sizePage;

    private int controlSize = 0;


    private static final String SECTION_XPATH = "//*[@id='wrapper']/div/section";
    @FindBy(xpath = CABINET_MENU)
    private WebElement cabinetMenu;
    @FindBy(xpath = ORDERS_HISTORY)
    private WebElement ordersHistory;
    @FindBy(xpath = SECTION_XPATH)
    private WebElement section;

    private int totalPages;
    private int sizeFirstPage;
    private int sizeLastPage;
    private int totalOrders;
    private WebElement pagination;
    public WebDriver driver;
    public Actions actions;




    @FindBy(xpath = "//*[@id=\"wrapper\"]/div/section/nav/ul/li[13]/a")
    private WebElement last;

    @FindBy(xpath = "/html/body/div[2]/div/section/nav/ul/li[14]/a")
    private WebElement BtnNext;

    public int lastElement(){
        int lastCount  = Integer.parseInt(last.getText()) - 1;
        return lastCount;
    }
    public void ClickBtnNext(){
        BtnNext.click();
    }




    public void AllCount(){
        for (int i = 0; i < totalPages - 1; i++) {
            pagination = driver.findElement(By.xpath("//span[@class='sr-only' and contains(text(),'Next')]"));

            parentElement = pagination.findElement(By.xpath("./.."));
            actions.moveToElement(parentElement).click().perform();
            List<WebElement> elements = section.findElements(By.xpath(".//*[@id]"));
            sizePage = elements.size() - 1;
            controlSize += sizePage;

        }
        writeDataToFile("output.txt");
    }

    public HistoryOfZakazPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);

    }

    public void writeDataToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Количество страниц: " + totalPages);
            writer.newLine();
            writer.write("Общее количество заказов: " + controlSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterHistory(){
        actions.moveToElement(cabinetMenu).perform();
        cabinetMenu.click();
        actions.moveToElement(ordersHistory).perform();
        ordersHistory.click();
    }

    public int checkHistoryPagesCount(){
        //*[@id="wrapper"]/div/section/nav/ul/li[13]/a
        pagination = driver.findElement(By.xpath("//*[@id='wrapper']/div/section/nav/ul/li[last()-1]"));
        if (pagination!= null) {
            totalPages = Integer.parseInt(pagination.getText().toString());
        } else {
            // Если пагинация отсутствует, считаем страницу единственной
            totalPages = 1;
        }

        return totalPages;
    }

    public int getOrdersCountFirstPage(){
        List<WebElement> elements = section.findElements(By.xpath(".//*[@id]"));
        sizeFirstPage=  elements.size()-1;//-1 так как там есть id=PaymentFormLoader
        return sizeFirstPage;
    }



    public int getOrdersCountSecondPage(){
        actions.moveToElement(pagination).perform();
        pagination.click();
        List<WebElement> elements = section.findElements(By.xpath(".//*[@id]"));
        sizeLastPage = elements.size()-1;//-1 так как там есть id=PaymentFormLoader
        return sizeLastPage;
    }

    public int getOrdersCount(){
        int first= getOrdersCountFirstPage();

        int second = getOrdersCountSecondPage();

        totalOrders = (first*(totalPages-1))+second;
        writeDataToFile("output.txt");
        return totalOrders;
    }



}

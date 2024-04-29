package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
public class LoginTest {
    public String FIO = "5518\nКузнецова";

    public String ProductID = "a7956";
    public static LoginPage loginPage;
    public static ProfileClass profilePage;
    public static HistoryOfZakazPage HistoryOfZakazPage;
    public static WebDriver driver;

    //public static CatalogPage catalogPage;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kriss\\Downloads\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfileClass(driver);
        HistoryOfZakazPage = new HistoryOfZakazPage(driver);
        //catalogPage = new CatalogPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //задержка на выполнение теста = 10 сек.
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage")); }
    /**
     * тестовый метод для осуществления аутентификации
     */
    @Test
    public void loginTest() throws InterruptedException {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин
        loginPage.clickLoginBtn();
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.LogInBtn();
        //получаем отображаемый логин
        //String user = profilePage.getUserName();
        //и сравниваем его с логином из файла настроек
        //Assert.assertEquals(FIO, user);
        //profilePage.entryMenu();
       /* catalogPage.OpenSort();
        catalogPage.ChooseRich();
        Thread.sleep(1500);


        */
        /*
        catalogPage.openBrend();
        catalogPage.chooseJcrew();
        catalogPage.openSeason();
        catalogPage.chooseSummer();
        String prodId = catalogPage.getUserName();
        Assert.assertEquals(prodId, ProductID);
        catalogPage.clearFilter();
         */
        HistoryOfZakazPage.enterHistory();
        HistoryOfZakazPage.checkHistoryPagesCount();
        HistoryOfZakazPage.AllCount();
        //HistoryOfZakazPage.getOrdersCount();


    }
    /**
     * осуществление выхода из аккаунта с последующим закрытием окна браузера
     */
    @AfterClass
    public static void tearDown() {
       // catalogPage.LogOut();
        driver.quit(); }

}

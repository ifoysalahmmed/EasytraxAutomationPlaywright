package pages;

import utilities.DriverSetup;

public class LoginPage extends DriverSetup {

    public String baseURL = "https://platform.easytrax.com.bd/auth";

    public String login_email = "//input[@id='email']";
    public String login_password = "//input[@id='password']";
    public String login_button = "//button[contains(text(),'Sign In')]";
    public String error_message = "//body/div[@id='root']/div[1]/div[1]/div[1]/form[1]/ul[1]/li[1]";

    public void navigateToLoginPage () throws InterruptedException {
        openPage (baseURL);
    }
}
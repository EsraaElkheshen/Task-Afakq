package suites;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import utlities.Constants;

public class RegisterationTest extends TestBase {
    HomePage homePage;
    AccountPage accountPage;
    @BeforeClass
    public void beforeClass() {
        homePage = new HomePage(driver);

    }
    /*
    This test used to verify Valid Register User
     */

    @Test(priority = 1)
    public void verifyValidRegisterUser() {
        homePage.signIn();
        accountPage = new AccountPage(driver);
        testDataReader.selectDataSet(Constants.SheetName);
        accountPage.enterEmail(testDataReader.getDataCollector().get("email"));
        accountPage.createNewAccount(testDataReader.getDataCollector().get("firstname"),
                testDataReader.getDataCollector().get("lastname"),
                testDataReader.getDataCollector().get("password"),
                testDataReader.getDataCollector().get("address"),
                testDataReader.getDataCollector().get("city"),
                testDataReader.getDataCollector().get("state"),
                testDataReader.getDataCollector().get("code"),
                testDataReader.getDataCollector().get("mobile"),
                testDataReader.getDataCollector().get("alias"));
        System.out.println("User login Successfully");
        System.out.println("Message :" + accountPage.getSuccessMsg());
        Assert.assertTrue(accountPage.isSuccessMsgDisplay());
    }

    /*
   This test used to signOut from Page
    */
    @Test(priority = 2, dependsOnMethods = {"verifyValidRegisterUser"})
    public void signOutPage() {
        homePage.signOut();
        Assert.assertTrue(homePage.isSignInDisplay());
        System.out.println("User Sign Out Successfully");
        System.out.println("Message :" + homePage.getMsgText());
    }

}

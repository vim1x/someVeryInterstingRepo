package Listeners;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;


public class AllureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Allure.attachment("Screenshot.png", Selenide.screenshot(OutputType.BASE64));
    }



}

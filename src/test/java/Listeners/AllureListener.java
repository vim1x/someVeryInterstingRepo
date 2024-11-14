package Listeners;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class AllureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        createFullPageScreenshot();
    }

    private void createFullPageScreenshot() {
        File file = Selenide.screenshot(OutputType.FILE);
        try {
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

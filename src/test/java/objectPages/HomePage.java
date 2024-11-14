package objectPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.collections.SizeGreaterThan;
import org.openqa.selenium.Keys;

import java.util.Date;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {
    public SelenideElement dismissButton = $("button[aria-label='Dismiss sign-in info.']").shouldBe(clickable);
    public SelenideElement searchField = $("input[name='ss']");
    public SelenideElement searchButton = $("button[type='submit']");
    ElementsCollection popupResults = $$("div[data-testid='autocomplete-result']");

    public HomePage() {
    }
    public void setSearchField(String name) {
        searchField.sendKeys(name);
        popupResults.shouldHave(sizeGreaterThan(0));
        popupResults.first().shouldHave(text(name));
        searchField.sendKeys(Keys.ENTER);
    }
    public void search() {
        searchButton.click();
    }
    public void goToFlights() {
        $("a[id='flights']").click();
    }
}

package objectPages;

import DTO.ApartmentDescriptionDTO;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ApartmentPage {
    public SelenideElement title = $("h2[class*='pp-header__title']");
    public SelenideElement reviewScore = $x("//div[@data-testid='review-score-right-component']//div[@class='a3b8729ab1 d86cee9b25']");
    public SelenideElement address = $x("//span[@data-component='tooltip']");
    ElementsCollection prices = $$("div[class*='bui-price-display__value']");

    public ApartmentDescriptionDTO getDescription() {
        switchTo().window(1);
        String name = title.shouldBe(visible).getText();
        int price = Integer.parseInt(prices.get(0).getText().replaceAll("[^0-9]", ""));
        int rating = Integer.parseInt(reviewScore.getText().replaceAll("[^0-9]", "")) / 100;
        String address = this.address.getText();
        String[] parts = address.split(",");
        String city = parts[parts.length - 3].trim();
        String district = parts[parts.length - 4].trim();
        address = district + ", " + city ;
        return new ApartmentDescriptionDTO(name, price, rating, address );
    }
}

package objectPages;

import DTO.ApartmentDescriptionDTO;
import DTO.ApartmentSearchDTO;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ApartmentsListPage {
    public SelenideElement forAllTimeLabel = $("label[for=':rq:-alt']");
    public SelenideElement twoStarsCheck = $("div[data-filters-item='class:class=2']");
    public SelenideElement threeStarsCheck = $("div[data-filters-item='class:class=3']");
    public SelenideElement fourStarsCheck = $("div[data-filters-item='class:class=4']");
    public SelenideElement fiveStarsCheck = $("div[data-filters-item='class:class=5']");
    public SelenideElement dateSearchBox = $("div[data-testid='searchbox-dates-container']'");
    public ElementsCollection dates = $$("td[role='gridcell']");
    public SelenideElement loadMoreDateButton = $("button[aria-label='Наступний місяць']");
    public SelenideElement searchButton = $("button[type='submit']");
    public ElementsCollection apartmentsStars = $$("div[class='b3f3c831be']");
    public SelenideElement sortersDropdownButton = $("button[data-testid='sorters-dropdown-trigger']");
    public SelenideElement sortByPriceButton = $("button[data-id='price']");
    public ElementsCollection apartmentsList = $$("div[data-testid='property-card-container']");
    public SelenideElement filterApartments = $$("div[data-filters-item='ht_id:privacy_type=3']").filter(visible).first();


    public void changeToAllTimePrice() {
        forAllTimeLabel.click();
    }

    public void setStars(int number) {
        if(number == 2)
            twoStarsCheck.click();
        if(number == 3)
            threeStarsCheck.click();
        if(number == 4)
            fourStarsCheck.click();
        if(number == 5)
            fiveStarsCheck.click();
    }

    public void search() {
        searchButton.click();
    }

    public void pickDates(String firstDate, String secondDate){ //YYYY-MM-DD
        dates = $$("td[role='gridcell']");
        dates.shouldHave(sizeGreaterThan(0));
        boolean picked = false;
        while (!picked) {
            // Refresh the collection of dates and filter for the target date
            SelenideElement targetDate = $$("td[role='gridcell'] span[role='checkbox']")
                    .filterBy(attribute("data-date", firstDate))
                    .first();

            if (targetDate.exists()) {
                // If the date is found, click it and set picked to true
                targetDate.click();
                picked = true;
            } else if (loadMoreDateButton.exists()) {
                // If the date is not found, click loadMoreDateButton to fetch more dates
                loadMoreDateButton.click();
            }
        }
        picked = false;
        while (!picked) {
            // Refresh the collection of dates and filter for the target date
            SelenideElement targetDate = $$("td[role='gridcell'] span[role='checkbox']")
                    .filterBy(attribute("data-date", secondDate))
                    .first();

            if (targetDate.exists()) {
                // If the date is found, click it and set picked to true
                targetDate.click();
                picked = true;
            } else if (loadMoreDateButton.exists()) {
                // If the date is not found, click loadMoreDateButton to fetch more dates
                loadMoreDateButton.click();
            }
        }
    }

    public boolean checkStars(int number) {
        String intendedStars = Integer.toString(number) + " out of 5";
        boolean result = true;
        apartmentsStars.shouldHave(sizeGreaterThan(0));
        for(SelenideElement apartmentStars: apartmentsStars) {
            System.out.println(apartmentStars.getAttribute("aria-label"));
            if(!Objects.equals(apartmentStars.getAttribute("aria-label"), intendedStars)) {
                result = false;
            }
        }
        return result;
    }

    public void sortByPrice() {
        sortersDropdownButton.click();
        sortByPriceButton.shouldBe(clickable, Duration.ofSeconds(2));
        sortByPriceButton.click();
    }

    public boolean checkSortingOfPrice() {
        boolean check = true;
        apartmentsList.shouldHave(sizeGreaterThan(0));
        int price0 = 0;
        int price1;
        for(SelenideElement apartment : apartmentsList) {
            price1 = Integer.parseInt(apartment
                    .$("span[data-testid='price-and-discounted-price']")
                    .getText()
                    .replaceAll("[^0-9]", ""));
            if(price1 < price0) {
                check = false;
            }
            price0 = price1;
        }
        return check;
    }

    public int getPrice(int number) {
        apartmentsList.shouldHave(sizeGreaterThan(0));
        SelenideElement apartment = apartmentsList.get(number);
        return Integer.parseInt(apartment
                .$("span[data-testid='price-and-discounted-price']")
                .getText()
                .replaceAll("[^0-9]", ""));
    }
    public void filterByApartments() {
        filterApartments.click();
    }
    public void goToApartment(int number) {
        apartmentsList.shouldHave(sizeGreaterThan(0));
        SelenideElement apartment = apartmentsList.get(number);
        apartment.$("a[data-testid='availability-cta-btn']").click();
    }
    public ApartmentDescriptionDTO getDescription(int number) {
        apartmentsList.shouldHave(sizeGreaterThan(0));
        SelenideElement apartment = apartmentsList.get(number);
        String name = apartment.$("div[data-testid='title']").getText();
        int price = getPrice(number);
        int rating = Integer.parseInt(apartment
                .$("div[data-testid='review-score']")
                .$("div[class='ac4a7896c7']")
                .getText()
                .replaceAll("[^0-9]", ""));
        String address = apartment.$("span[data-testid='address']").getText();
        return new ApartmentDescriptionDTO(name, price, rating, address);
    }
}

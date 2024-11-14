package tests;

import DTO.ApartmentSearchDTO;
import Listeners.AllureListener;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class, AllureListener.class}) // Add your listener and Allure's
public class SortPriceTest extends TestInit{
    @DataProvider(name = "checkSortProvider")
    public Object[][] checkSortProvider() {
        return new Object[][]{
                { new ApartmentSearchDTO("London", "2024-12-10", "2024-12-11") },
                { new ApartmentSearchDTO("London", "2024-12-20", "2024-12-30") },
                { new ApartmentSearchDTO("Warsaw", "2024-11-20", "2024-12-20") },
                { new ApartmentSearchDTO("Paris", "2024-11-17", "2024-11-19") }
        };
    }

    @Test(dataProvider = "checkSortProvider")
    public void checkSort(ApartmentSearchDTO data) {
        homePage.setSearchField(data.getCity());
        apartmentsListPage.pickDates(data.getStartDate(), data.getEndDate());
        apartmentsListPage.search();
        apartmentsListPage.sortByPrice();
        boolean check = apartmentsListPage.checkSortingOfPrice();
        Assert.assertTrue(check, "Sorting by price doesn't work as intended");
    }
}

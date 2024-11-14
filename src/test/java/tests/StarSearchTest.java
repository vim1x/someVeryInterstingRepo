package tests;

import DTO.ApartmentSearchDTO;
import Listeners.AllureListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureListener.class)
public class StarSearchTest extends TestInit{
    @DataProvider(name = "starCheckProvider")
    public Object[][] starCheckProvider() {
        return new Object[][]{
                { new ApartmentSearchDTO("London", "2024-11-10", "2024-12-01", 2) },
                { new ApartmentSearchDTO("London", "2024-12-10", "2024-12-11", 3) },
                { new ApartmentSearchDTO("Warsaw", "2024-11-20", "2024-12-20", 4) },
                { new ApartmentSearchDTO("Paris", "2024-11-15", "2024-11-17", 5) }
        };
    }

    @Test(dataProvider = "starCheckProvider")
    public void starCheckTest(ApartmentSearchDTO data) {
        homePage.setSearchField(data.getCity());
        apartmentsListPage.pickDates(data.getStartDate(), data.getEndDate());
        apartmentsListPage.setStars(data.getStars());

        boolean check = apartmentsListPage.checkStars(data.getStars());
        Assert.assertNotEquals(data.getCity(), "Paris");
        Assert.assertTrue(check, "Stars filter did not work as expected for " + data.getStars() + "-star apartments.");
    }
}

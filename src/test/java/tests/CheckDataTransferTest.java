package tests;

import DTO.ApartmentDescriptionDTO;
import Listeners.AllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureListener.class)
public class CheckDataTransferTest extends TestInit{
    @Test
    public void DataTransferTest() {
        homePage.setSearchField("Warsaw");
        apartmentsListPage.pickDates("2024-12-10", "2024-12-20");
        apartmentsListPage.search();
        ApartmentDescriptionDTO description0 = apartmentsListPage.getDescription(0);
        apartmentsListPage.goToApartment(0);
        ApartmentDescriptionDTO description1 = apartmentPage.getDescription();
        Assert.assertTrue(ApartmentDescriptionDTO.areEqual(description0, description1));
    }

}

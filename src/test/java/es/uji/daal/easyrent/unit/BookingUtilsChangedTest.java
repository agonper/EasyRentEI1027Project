package es.uji.daal.easyrent.unit;

import es.uji.daal.easyrent.utils.AvailabilityChanges;
import es.uji.daal.easyrent.utils.BookingUtils;
import es.uji.daal.easyrent.utils.DateUtils;
import es.uji.daal.easyrent.view_models.BookingForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alberto on 15/06/2016.
 */
@RunWith(Parameterized.class)
public class BookingUtilsChangedTest {

    private final Date startDate;
    private final Date endDate;
    private final int expectedToSave;
    private final int expectedToRemove;


    public BookingUtilsChangedTest(Date startDate, Date endDate, int expectedToSave, int expectedToRemove) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedToSave = expectedToSave;
        this.expectedToRemove = expectedToRemove;
    }

    @Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][] {
                {createDate(20, 6, 2016), createDate(20, 6, 2016), 0, 1},
                {createDate(15, 6, 2016), createDate(18, 6, 2016), 0, 1},
                {createDate(16, 6, 2016), createDate(18, 6, 2016), 1, 0},
                {createDate(16, 6, 2016), createDate(17, 6, 2016), 2, 0},
                {createDate(30, 6, 2016), createDate(3, 7, 2016), 2, 0},
                {createDate(24, 6, 2016), createDate(25, 6, 2016), 1, 1},
                {createDate(22, 6, 2016), createDate(25, 6, 2016), 0, 3},
        });
    }

    private static List getAvailabilityPeriods() {
        return BookingUtilsValidatorTest.getAvailabilityPeriods();
    }

    private static Date createDate(int day, int month, int year) {
        return DateUtils.createDate(day, month, year);
    }

    @Test
    public void isDatePeriodAvailable() throws Exception {
        BookingForm form = new BookingForm();
        form.setStartDate(startDate);
        form.setEndDate(endDate);

        AvailabilityChanges changes = BookingUtils.getChanges(form, getAvailabilityPeriods());
        assertThat(changes.getToBeSaved().size(), is(expectedToSave));
        assertThat(changes.getToBeRemoved().size(), is(expectedToRemove));
    }

}
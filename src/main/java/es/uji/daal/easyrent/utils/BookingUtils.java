package es.uji.daal.easyrent.utils;

import es.uji.daal.easyrent.model.AvailabilityPeriod;
import es.uji.daal.easyrent.view_models.BookingForm;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Alberto on 15/06/2016.
 */
public class BookingUtils {

    public static boolean isDatePeriodAvailable(BookingForm form, Collection<AvailabilityPeriod> periods) {
        Set<AvailabilityPeriod> orderedPeriods = new TreeSet<>((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
        orderedPeriods.addAll(periods);

        boolean interPeriod = false;
        Iterator<AvailabilityPeriod> it = orderedPeriods.iterator();
        while (it.hasNext()) {
            AvailabilityPeriod period = it.next();
            if (isInsidePeriod(form, period)) {
                return true;
            }

            if (isInsidePeriod(form, period)) {
                interPeriod = true;
            }

            if (interPeriod) {
                return isEndingInPeriod(form, period);
            }
        }

        return false;
    }

    private static boolean isInsidePeriod(BookingForm form, AvailabilityPeriod period) {
        if (compareStartDates(form, period) >= 0) {
            if (period.getEndDate() == null) {
                return true;
            }
            if (compareEndDates(form, period) <= 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isStartingInPeriod(BookingForm form, AvailabilityPeriod period) {
        if (compareStartDates(form, period) >= 0) {
            if (compareEndDates(form, period) > 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEndingInPeriod(BookingForm form, AvailabilityPeriod period) {
        if (compareStartDates(form, period) < 0) {
            if (period.getEndDate() == null) {
                return true;
            }
            if (compareEndDates(form, period) <= 0) {
                return true;
            }
        }
        return false;
    }

    private static int compareEndDates(BookingForm form, AvailabilityPeriod period) {
        return form.getEndDate().compareTo(period.getEndDate());
    }

    private static int compareStartDates(BookingForm form, AvailabilityPeriod period) {
        return form.getStartDate().compareTo(period.getStartDate());
    }
}

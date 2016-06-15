package es.uji.daal.easyrent.validators;

import es.uji.daal.easyrent.model.AvailabilityPeriod;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.utils.BookingUtils;
import es.uji.daal.easyrent.view_models.BookingForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;

/**
 * Created by daniel on 30/04/16.
 */
public class BookingValidator implements Validator {

    private final Collection<AvailabilityPeriod> periods;

    public BookingValidator(Collection<AvailabilityPeriod> periods) {
        this.periods = periods;
    }

    @Override
    public boolean supports(Class<?> cls) {
        return BookingForm.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        BookingForm form = (BookingForm)obj;

        if (form.getStartDate() == null) {
            errors.rejectValue("startDate", "invalid", "Start date cannot be empty.");
        }

        if (form.getEndDate() == null) {
            errors.rejectValue("startDate", "invalid", "End date cannot be empty.");
        }

        if (form.getStartDate() != null && form.getEndDate() != null && !BookingUtils.isDatePeriodAvailable(form, periods)) {
            errors.rejectValue("startDate", "invalid", "The chosen period is not available.");
        }
    }
}

package es.uji.daal.easyrent.validators;

import es.uji.daal.easyrent.view_models.PersonalDataForm;
import es.uji.daal.easyrent.view_models.SignupForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by daniel on 30/04/16.
 */
public class PersonalDataValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return PersonalDataForm.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        PersonalDataForm form = (PersonalDataForm) obj;

        // FIXME Tenia que hacerlo :P
        if (form.getName().equals("")) {
            errors.rejectValue("name", "invalid", "A man needs a name.");
        }

        if (!form.getDni().equals("") && !form.getDni().matches("[0-9]{8}[A-Za-z]")) {
            errors.rejectValue("dni", "invalid", "Invalid NID. Format: 8 numbers and 1 letter.");
        }

        if (form.getCountryPrefix().equals("") && !form.getPhoneNumber().equals("")) {
            errors.rejectValue("countryPrefix", "invalid", "You must introduce a country prefix.");
        }

        if (form.getPhoneNumber().equals("") && !form.getCountryPrefix().equals("")) {
            errors.rejectValue("phoneNumber", "invalid", "You must introduce a phoneNumber.");
        }

        if (!form.getCountryPrefix().equals("") && !form.getCountryPrefix().matches("\\+[0-9]{2}")) {
            errors.rejectValue("countryPrefix", "invalid", "The prefix must follow +XX format.");
        }

        if (!form.getPhoneNumber().equals("") && !form.getPhoneNumber().matches("[0-9]{9}")) {
            errors.rejectValue("phoneNumber", "invalid", "Invalid phone number.");
        }
    }
}

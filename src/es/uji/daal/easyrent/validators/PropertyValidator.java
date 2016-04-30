package es.uji.daal.easyrent.validators;

import es.uji.daal.easyrent.models.Property;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by daniel on 23/04/16.
 */
public class PropertyValidator implements Validator {

    String positiveAndLessThan100 = "It's necessary a positive number and less than 100.";
    String positiveAndLessThan1000 = "It's necessary a positive number and less than 1000.";
    String positiveAndLessThan10000 = "It's necessary a positive number and less than 10000.";

    @Override
    public boolean supports(Class<?> aClass) {
        return Property.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Property property = (Property)o;

        if (property.getTitle().equals("")) {
            errors.rejectValue("title", "required", "It's necessary to introduce a value.");
        }

        //TODO: Revisar si hacerlo con un campo o un selector
        if (property.getLocation().equals("")) {

        }

        if (property.getRooms() <= 0 || property.getRooms() > 99) {
            errors.rejectValue("rooms", "required", positiveAndLessThan100);
        }

        if (property.getCapacity() <= 0 || property.getCapacity() > 99) {
            errors.rejectValue("capacity", "required", positiveAndLessThan100);
        }

        if (property.getBeds() <= 0 || property.getBeds() > 99) {
            errors.rejectValue("beds", "required", positiveAndLessThan100);
        }

        if (property.getBathrooms() <= 0 || property.getBathrooms() > 99) {
            errors.rejectValue("bathrooms", "required", positiveAndLessThan100);
        }

        if (property.getFloorSpace() <= 0.0 || property.getFloorSpace() >= 1000.0) {
            errors.rejectValue("floorSpace", "required", positiveAndLessThan1000);
        }

        if (property.getPricePerDay() <= 0.0 || property.getPricePerDay() >= 10000.0) {
            errors.rejectValue("pricePerDay", "required", positiveAndLessThan10000);
        }

        //TODO: Tipo no seleccionado
        if (property.getType() == null) {

        }
    }
}

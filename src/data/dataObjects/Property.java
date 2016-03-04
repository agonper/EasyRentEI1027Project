package data.dataObjects;

import javax.management.InvalidAttributeValueException;
import java.util.Date;

public class Property {

    private final int propertyID;
    private String title;
    private String location;
    private int rooms;
    private int capacity;
    private int beds;
    private int bathrooms;
    private int floorSpace;
    private float pricePerDay;
    private String type;
    private String description;

    private Date creationDate;
    private Date availabilityLimit;


    public Property(int propertyID, String title, String location, int rooms,
                    int capacity, int beds, int bathrooms, int floorSpace,
                    float pricePerDay, String type, String description, Date creationDate,
                    Date availabilityLimit) throws InvalidAttributeValueException {

        String attributesValidity = this.checkConstructorAttributes(propertyID, title, location, rooms, capacity, beds, bathrooms,
                floorSpace, pricePerDay, type, description, creationDate, availabilityLimit);

        if (attributesValidity == null) {
            this.propertyID = propertyID;
            this.title = title;
            this.location = location;
            this.rooms = rooms;
            this.capacity = capacity;
            this.beds = beds;
            this.bathrooms = bathrooms;
            this.floorSpace = floorSpace;
            this.pricePerDay = pricePerDay;
            this.type = type;
            this.description = description;
            this.creationDate = creationDate;
            this.availabilityLimit = availabilityLimit;
        }

        else
            throw new InvalidAttributeValueException(attributesValidity);
    }

    /**
     * -------------------
     * --- Use methods ---
     * -------------------
     */

    public boolean isAvailableForDay(Date dayToCheckAvailability) {

        if (this.availabilityLimit != null)
            return this.availabilityLimit.after(dayToCheckAvailability);

        else
            return true;
    }

    /**
     * -------------------------
     * --- Auxiliary methods ---
     * -------------------------
     */

    private String checkConstructorAttributes(int propertyID, String title, String location, int rooms,
                                                  int capacity, int beds, int bathrooms, int floorSpace,
                                                  float pricePerDay, String type, String description,
                                                 Date creationDate, Date availabilityLimit) {

        boolean correctPropertyID = propertyID > 0 && propertyID <= 99999999;
        if (!correctPropertyID)
            return "The introduced property ID format is not correct";

        boolean correctTitle = title != null && title.length() <= 20;
        if (!correctTitle)
            return "The introduced property title format is not correct";

        boolean correctLocation = location != null && location.length() <= 15;
        if (!correctLocation)
            return "The introduced property location format is not correct";

        boolean correctRooms = rooms > 0 && rooms <= 99;
        if (!correctRooms)
            return "The introduced property rooms format is not correct";

        boolean correctCapacity = capacity > 0 && capacity <= 99;
        if (!correctCapacity)
            return "The introduced property capacity format is not correct";

        boolean correctBeds = beds > 0 && beds <= 99;
        if (!correctBeds)
            return "The introduced property number of beds format is not correct";

        boolean correctBathrooms = bathrooms > 0 && bathrooms <= 99;
        if (!correctBathrooms)
            return "The introduced property number of bathrooms format is not correct";

        boolean correctFloorSpace = floorSpace > 0 && floorSpace <= 999;
        if (!correctFloorSpace)
            return "The introduced property floor space format is not correct";

        boolean correctPricePerDay = pricePerDay > 0.0 && floorSpace <= 9999.9999;
        if (!correctPricePerDay)
            return "The introduced property price per day format is not correct";

        boolean correctType = type != null && type.length() <= 20;
        if (!correctType)
            return "The introduced property type format is not correct";

        boolean correctDescription = description != null && description.length() <= 500;
        if (!correctDescription)
            return "The introduced property description format is not correct";

        boolean correctCreationDate = creationDate != null;
        if (!correctCreationDate)
            return "The introduced property creation date format is not correct";

        boolean correctAvailabilityLimit = true;

        if (availabilityLimit != null)
            correctAvailabilityLimit = availabilityLimit.after(creationDate);

        if (!correctAvailabilityLimit)
            return "The introduced property availability date is not correct";

        return null;
    }
}

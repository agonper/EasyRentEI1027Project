package dataTests.doTests;

import data.dataObjects.Property;
import org.junit.Test;

import javax.management.InvalidAttributeValueException;
import java.util.Date;

public class propertyTest {

    @Test
    public final void propertyConstructorTest() {
        Property propertyTest = null;
        Date actualDate = new Date(800000L);

        int[] propertyBadIDs = {-1, 0, 100000000};
        int[] propertyCorrectIDs = {1, 50, 99999999};

        //TODO: Comprobar condiciones -> ¿Uso de números o solo números?
        String[] propertyBadTitles = {null, "", "abcdefghijklmnñopqrstuvwxyz"};
        String[] propertyCorrectTitles = {"Prueba", "Casa X", "12345678901234567890"};

        //TODO: Debatir uso de ENUM
        String[] propertyBadLocations = {null, "", "abcdefghijklmnñopqrstuvwxyz"};
        String[] propertyCorrectLocations = {"Castellón", "España", "V"};

        int[] propertyBadRooms = {-1, 0, 100};
        int[] propertyCorrectRooms = {1, 5, 99};

        int[] propertyBadCapacities = {-1, 0, 100};
        int[] propertyCorrectCapacities = {1, 10, 99};

        int[] propertyBadBeds = {-1, 0, 100};
        int[] propertyCorrectBeds = {1, 10, 99};

        int[] propertyBadBathrooms = {-1, 0, 100};
        int[] propertyCorrectBathrooms = {1, 5, 99};

        int[] propertyBadFloorSpaces = {-1, 0, 1000};
        int[] propertyCorrectFloorSpaces = {1, 100, 999};

        float[] propertyBadPrices = {-1.0f, 0.0f, 10000.0f};
        float[] propertyCorrectPrices = {0.001f, 10.f, 9999.9f};

        //TODO: Debatir el uso de ENUM -> Tipos de propiedades
        String[] propertyBadTypes = {null, "", "abcdefghijklmnñopqrstuvwxyz"};
        String[] propertyCorrectTypes = {"Bungalow", "Piso", "V"};

        String[] propertyBadDescriptions = {null, "", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In turpis felis, suscipit a libero quis, tincidunt feugiat sapien. Duis at arcu interdum, luctus velit ut, pellentesque nunc. Cras iaculis blandit leo. Suspendisse scelerisque felis non placerat mattis. Cras ac sagittis justo. Sed cursus, purus id interdum viverra, risus erat congue erat, at tincidunt purus elit sed tortor. Suspendisse non dictum diam. Nulla ullamcorper cursus lacinia. Nam blandit cursus purus, ut placerat massa ornare sit amet. Etiam pellentesque iaculis tortor ac feugiat.\n" +
                "\n" +
                "Nam tellus neque, mollis ut semper eu, pulvinar in est. Phasellus tempor gravida congue. In hac habitasse platea dictumst. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam et suscipit enim. Aliquam ultrices ornare ultricies. Donec nec lacus vehicula, suscipit tortor ut, consequat dolor. In eu velit congue est imperdiet finibus. Praesent pretium nisl diam, ut commodo neque dictum sit amet. In sodales mi at magna vulputate, et convallis dui scelerisque. Donec orci nunc, aliquam sed lorem eget, sagittis laoreet tellus. Quisque tortor justo, viverra quis enim quis, suscipit venenatis metus. Integer ullamcorper malesuada mi. Proin vehicula nunc dolor, et porta ligula ultrices nec. Aliquam auctor semper dolor, volutpat pulvinar nisl tempus id. Pellentesque efficitur est erat, in viverra elit condimentum nec.\n" +
                "\n" +
                "Nunc sodales maximus quam vel tincidunt. Etiam ultrices diam diam, ut tincidunt lectus lobortis vitae. Curabitur in metus suscipit dolor vehicula efficitur iaculis non tortor. Donec at felis eleifend, aliquam libero et, consequat leo. Quisque tristique hendrerit rutrum. Vivamus dictum est at consequat mattis. Mauris scelerisque at dolor eget ultricies. Nullam turpis mauris, suscipit ut scelerisque at, fermentum quis lorem. In neque metus, pellentesque ac consectetur eget, ultrices ut erat.\n" +
                "\n" +
                "Sed pellentesque blandit nunc quis cursus. Mauris lacinia, sapien ac condimentum imperdiet, lectus ante gravida enim, eu ultrices odio ex in augue. Quisque mattis lorem congue erat semper lacinia. Aliquam tincidunt ipsum risus, quis accumsan leo iaculis a. Praesent egestas erat et nisl iaculis, in vehicula lectus vestibulum. Vivamus a placerat lorem. Mauris commodo ornare diam, in lacinia magna egestas at. Fusce et venenatis odio, ac efficitur quam. Duis finibus nisi sed metus mattis, id consectetur diam ullamcorper.\n" +
                "\n" +
                "Ut dictum lectus ante, sed ornare lorem vestibulum at. Fusce gravida scelerisque lorem in ultricies. In consequat, mi quis fermentum placerat, massa nunc porttitor metus, non pharetra libero lacus eu ex. Suspendisse quis nisl sit amet est egestas sollicitudin. Quisque nec felis a enim varius semper quis quis lacus. Vivamus placerat erat sed odio rutrum commodo. Nam id orci at mi commodo vehicula. Praesent tincidunt dictum augue non dapibus.\n" +
                "\n" +
                "Fusce dapibus lacus a luctus euismod. Sed suscipit pretium eros non congue. Phasellus sodales diam sit amet quam scelerisque, in egestas enim dignissim. Aenean porta iaculis tellus, at rutrum mauris maximus ac. Donec eget viverra neque, ac eleifend leo. Nunc sollicitudin, ligula id malesuada consectetur, nisi ex auctor nisl, ut iaculis nunc nibh et magna. Praesent convallis at dolor non placerat. Nullam nisi orci, eleifend eget gravida vel, venenatis at sem. Proin sagittis, metus eget."};
        String[] propertyCorrectDescriptions = {"1", "500CharactersLorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.", "W!342d"};

        Date[] propertyBadCreationDates = {null};
        Date[] propertyCorrectCreationDates = {new Date(actualDate.getTime() - 1L)};

        Date[] propertyBadAvailabilityDates = {new Date(propertyCorrectCreationDates[0].getTime() - 10L)};
        Date[] propertyCorrectAvailabilityDates = {null, new Date(propertyCorrectCreationDates[0].getTime() + 10L)};

        for (int i = 0; i < propertyBadIDs.length; i++) {
            try {
                propertyTest = new Property(propertyBadIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property ID format is not correct");
            }
        }

        for (int i = 0; i < propertyBadTitles.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyBadTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property title format is not correct");
            }
        }

        for (int i = 0; i < propertyBadLocations.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyBadLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property location format is not correct");
            }
        }

        for (int i = 0; i < propertyBadRooms.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyBadRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property rooms format is not correct");
            }
        }

        for (int i = 0; i < propertyBadCapacities.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyBadCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property capacity format is not correct");
            }
        }

        for (int i = 0; i < propertyBadBeds.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyBadBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property number of beds format is not correct");
            }
        }

        for (int i = 0; i < propertyBadBathrooms.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyBadBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property number of bathrooms format is not correct");
            }
        }

        for (int i = 0; i < propertyBadFloorSpaces.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyBadFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property floor space format is not correct");
            }
        }

        for (int i = 0; i < propertyBadPrices.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyBadPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property price per day format is not correct");
            }
        }

        for (int i = 0; i < propertyBadTypes.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyBadTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property type format is not correct");
            }
        }

        for (int i = 0; i < propertyBadDescriptions.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyBadDescriptions[i], propertyCorrectCreationDates[0], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property description format is not correct");
            }
        }

        for (int i = 0; i < propertyBadCreationDates.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyBadCreationDates[i], null);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property creation date format is not correct");
            }
        }

        for (int i = 0; i < propertyBadAvailabilityDates.length; i++) {
            try {
                propertyTest = new Property(propertyCorrectIDs[i], propertyCorrectTitles[i], propertyCorrectLocations[i], propertyCorrectRooms[i], propertyCorrectCapacities[i], propertyCorrectBeds[i], propertyCorrectBathrooms[i], propertyCorrectFloorSpaces[i], propertyCorrectPrices[i], propertyCorrectTypes[i], propertyCorrectDescriptions[i], propertyCorrectCreationDates[i], propertyBadAvailabilityDates[i]);
            }
            catch (InvalidAttributeValueException ex) {
                assert ex.getMessage().equals("The introduced property availability date is not correct");
            }
        }

    }

    //TODO: Create Property methods tests

}

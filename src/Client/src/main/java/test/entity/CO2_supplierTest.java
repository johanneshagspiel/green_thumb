package test.entity;
import entity.CO2_Supplier;

import org.junit.Test;
import static org.junit.Assert.*;

public class CO2_supplierTest {
    @Test
    public void testBikeNegative(){
        int km = -10;
        assertEquals("a negative value gives no saved co2", CO2_Supplier.bike(km), 0.0, 0);
    }

    @Test
    public void testBikePositive(){
        int km = 10;
        assertEquals("the km value should be multiplied by .016", CO2_Supplier.bike(km), km * .016, 0);
    }

    @Test
    public void testCarNegative(){
        int km = -10;
        assertEquals("Negative numbers should just return 0", CO2_Supplier.car(km), 0, 0);
    }

    @Test
    public void testCarPostive(){
        int km = 10;
        assertEquals("km should be multiplied by .271", CO2_Supplier.car(km), km * .271, 0);
    }

    @Test
    public void testPublicTransportNegative(){
        int km = -10;
        assertEquals("negative should return 0", CO2_Supplier.publicTransport(km), 0, 0);
    }

    @Test
    public void testPublicTransportPositive(){
        int km = 10;
        assertEquals("km should return multiplied by .101", CO2_Supplier.publicTransport(km), km * .101, 0);
    }

    @Test
    public void testBikeVsCarNegative(){
        int km = -10;
        assertEquals("the difference should be 0", CO2_Supplier.bikeVsCar(km), 0, 0);
    }

    @Test
    public void testBikeVsCarPositive(){
        int km = 10;
        double difference = 2.55;
        assertEquals("difference should be km * .271 - km * .016", CO2_Supplier.bikeVsCar(km), difference, 0);
    }

    @Test
    public void testPublicTransportVsCarNegative(){
        int km = -10;
        double difference = 0;
        assertEquals("negative km's should return 0", CO2_Supplier.publicTransportVsCar(km), 0, 0);
    }

    @Test
    public void testPublicTransportVsCarPositive(){
        int km = 10;
        double difference = 1.7;
        assertEquals("difference should be .271 * 10 - .101 * 10", CO2_Supplier.publicTransportVsCar(km), difference, 0);
    }

    @Test
    public void testBikeVsPublicTransportNegative(){
        int km = -10;
        assertEquals("negative input should return 0", CO2_Supplier.bikeVsPulbicTransport(km), 0, 0);
    }

    @Test
    public void testBikeVsPublicTransportPositive(){
        int km = 10;
        double difference = .85;
        assertEquals("should return .101*10 - .016*10", CO2_Supplier.bikeVsPulbicTransport(km), difference, 0);
    }

    @Test
    public void testLocalVegetarianMeal(){
        double CO2 = 1.2065;
        assertEquals("Should return 1.2065",CO2_Supplier.meal(true,true), CO2, 0);
    }
    @Test
    public void testNormalMeal(){
        double CO2 = 1.8748;
        assertEquals("should return 1.9749, since that is how much a normal mael costs", CO2_Supplier.meal(false, false), CO2, 0);
    }

    @Test
    public void testMealDifference(){
        double difference = 1.8748 - 1.2065;
        assertEquals("mealDifference should return a double equal to the difference variable", CO2_Supplier.mealDifference(true, true), difference, 0);
    }

    @Test
    public void testSolarpanelNegative(){
        int solarpanels = -5;
        assertEquals("negative amoount of panels should return 0", CO2_Supplier.solarPanel(solarpanels), 0, 0);
    }

    @Test
    public void testSolarpanelPositive(){
        int solarpanels = 5;
        double difference = 4.5 * solarpanels * .3 * .558;
        assertEquals("should return difference", CO2_Supplier.solarPanel(solarpanels), difference, 0);
    }

    @Test
    public void testTemperatureNegativeInhabitants(){
        assertEquals("Negative inhabitants returns 0", CO2_Supplier.temperature(-5, 1),0,0);
    }

    @Test
    public void testTemperatureNegativeDegrees(){
        assertEquals("should return 0", CO2_Supplier.temperature(1, -1), 0, 0);
    }

    @Test
    public void testTemperatureOneDegree(){
        assertEquals("sadf", CO2_Supplier.temperature(2, 1), .35467, .0001);
    }

    @Test
    public void testTemperatureMultipleDegrees(){
        double result = ((4000.0/365.0) -(4000.0/365.0)*.92*.92*.92)*.558;
        assertEquals("test wheter it works with muliple degrees difference", CO2_Supplier.temperature(3, 3), result, .0001);
    }

    @Test
    public void testUsedTemperatureOneDegree(){
        double result = (2900.0 /365.0) * .92 * .558;
        assertEquals("it should return the amount of CO2 saved", CO2_Supplier.usedTemperature(2,1), result, 0.001);
    }

    @Test
    public void testUsedTemperatureMultipleDegrees(){
        double result = (2900.0 /365.0) * .92 * .92 * .558;
        assertEquals("it should return the amount of CO2 saved", CO2_Supplier.usedTemperature(2,2), result, 0.001);
    }
}

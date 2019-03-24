import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by sammiexiu on 3/23/19.
 */
public class SolutionTest {

    protected ArrayList<Car> cars = new ArrayList<Car>();

    private ArrayList<Car> LoadResponse(String input_json_file) throws FileNotFoundException, ParseException {
        ArrayList<Car> cars = new ArrayList<Car>();
        JSONParser parser = new JSONParser();
        JSONArray cars_objects = (JSONArray) parser.parse(new FileReader(input_json_file));

        for (Object o : cars_objects) {
            JSONObject car_object = (JSONObject) o;
            Car car = new Car(car_object);
            cars.add(car);
            System.out.println("Added car: " + car.DebugString());
        }
        System.out.println("Loaded " + cars.size() + " cars.\n");

        return cars;
    }

    @Before
    public void setUp() throws Exception {
        cars = LoadResponse("./src/test/sample.json");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getHighestRevenueCar() throws Exception {
        ArrayList<Car> actual = Solution.GetHighestRevenueCar(cars);
        int size = actual.size();
        String first = actual.get(0).vin;
        Assert.assertEquals(1, size);
        Assert.assertEquals("1235", first);
    }

    @Test
    public void getLowestDailyRentalAfterDiscount() throws Exception {
        ArrayList<Car> actual = Solution.GetLowestDailyRentalAfterDiscount(cars);
        int actual_size = actual.size();
        double actual_lowest_price = actual.get(0).getCarDiscountedPrice();
        Assert.assertEquals(2, actual_size);
        Assert.assertEquals(125.0, actual_lowest_price, 0.0001);
    }

    @Test
    public void getLowestDailyRental() throws Exception {
        ArrayList<Car> actual = Solution.GetLowestDailyRental(cars);
        int actual_size = actual.size();
        double actual_lowest_price = actual.get(0).perdayrent.price;
        Assert.assertEquals(1, actual_size);
        Assert.assertEquals(130.0, actual_lowest_price, 0.0001);
    }

    @Test
    public void getBlueTeslas() throws Exception {
        ArrayList<Car> actual = Solution.GetBlueTeslas(cars);
        Assert.assertEquals(3, actual.size());
    }

}

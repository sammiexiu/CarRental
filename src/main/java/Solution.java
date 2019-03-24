/**
 * Created by sammiexiu on 3/22/19.
 */
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Solution {

    public static ArrayList<Car> GetHighestRevenueCar(ArrayList<Car> cars) {
        ArrayList<Car> highest_profit_cars =  new ArrayList<Car>();
        ArrayList<Float> profits = new ArrayList<Float>();
        for (Car car: cars) {
            profits.add(car.getCarRevenue());
        }

        Float hightest_profit = profits.get(0);
        for (Float profit : profits) {
            if (profit > hightest_profit) {
                hightest_profit = profit;
            }
        }

        for (Car car : cars) {
            if (car.getCarRevenue().equals(hightest_profit)) {
                highest_profit_cars.add(car);
            }
        }

        return highest_profit_cars;
    }

    public static ArrayList<Car> GetLowestDailyRentalAfterDiscount(ArrayList<Car> cars) {
        ArrayList<Car> lowest_discounted_price_cars =  new ArrayList<Car>();
        ArrayList<Float> prices_after_distcount = new ArrayList<Float>();

        // get the discouted prices of all cars
        for (Car car: cars) {
            Float discounted_price = car.perdayrent.price - car.perdayrent.discount;
            prices_after_distcount.add(discounted_price);
        }

        Float lowest_price = prices_after_distcount.get(0);
        for (Float price : prices_after_distcount) {
            if (price < lowest_price) {
                lowest_price = price;
            }
        }

        for (Car car : cars) {
            Float discounted_price = car.perdayrent.price - car.perdayrent.discount;
            if (discounted_price.equals(lowest_price)) {
                lowest_discounted_price_cars.add(car);
            }
        }

        return lowest_discounted_price_cars;
    }

    public static ArrayList<Car> GetLowestDailyRental(ArrayList<Car> cars) {
        ArrayList<Car> lowest_price_cars =  new ArrayList<Car>();
        ArrayList<Float> prices = new ArrayList<Float>();

        // get the prices of all cars
        for (Car car: cars) {
            prices.add(car.perdayrent.price);
        }

        Float lowest_price = prices.get(0);
        for (Float price : prices) {
            if (price < lowest_price) {
                lowest_price = price;
            }
        }

        for (Car car : cars) {
            if (car.perdayrent.price == lowest_price) {
                lowest_price_cars.add(car);
            }
        }

        return lowest_price_cars;
    }

    public static ArrayList<Car> GetBlueTeslas(ArrayList<Car> cars) {
        ArrayList<Car> blueTeslas =  new ArrayList<Car>();
        for(Car car : cars) {
            if (car.make.toLowerCase().equals("tesla") &&
                    car.metadata.color.toLowerCase().equals("blue")) {
                blueTeslas.add(car);
            }
        }
        return blueTeslas;
    }

    public void PrintAnsToQuestion1(ArrayList<Car> blueTeslas) {
        for (Car car: blueTeslas) {
            System.out.println( " Make: " + car.make + " Color: " + car.metadata.color + "Vin: " + car.vin);
        }
            }
    
}

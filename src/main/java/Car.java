import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sammiexiu on 3/22/19.
 */
public class Car {

    public String make;
    public String model;
    public String vin;
    public Metadata metadata;
    public Metrics metrics;
    public Perdayrent perdayrent;

    public class Perdayrent {
        public float price;
        public float discount;

        public Perdayrent(JSONObject o){
            price = o.getAsNumber("Price").floatValue();
            discount = o.getAsNumber("Discount").floatValue();
        }
    }


    public class Metadata {
        public String color;
        public String notes;

        public Metadata(JSONObject o) {
            color = o.getAsString("Color");
            notes = o.getAsString("Notes");
        }
    }

    public class Metrics {
        public class RentalCount {
            public int last_week;
            public int year_to_date;

            public RentalCount(JSONObject o) {
                last_week = o.getAsNumber("lastweek").intValue();
                year_to_date = o.getAsNumber("yeartodate").intValue();
            }
        }
        float yoy_maintenance_cost;
        float depreciation;
        RentalCount rental_count;

        public Metrics(JSONObject o) {
            yoy_maintenance_cost = o.getAsNumber("yoymaintenancecost").floatValue();
            depreciation = o.getAsNumber("depreciation").floatValue();
            rental_count = new RentalCount((JSONObject)o.get("rentalcount"));
        }
    }

    public Car() {}

    public Car(JSONObject o) {
        make = o.getAsString("make");
        model = o.getAsString("model");
        vin = o.getAsString("vin");
        perdayrent = new Perdayrent((JSONObject) o.get("perdayrent"));
        metadata = new Metadata((JSONObject) o.get("metadata"));
        metrics = new Metrics((JSONObject) o.get("metrics"));
    }

    public Float getCarRevenue(){
        Float cost = metrics.yoy_maintenance_cost + metrics.depreciation;
        return perdayrent.price * metrics.rental_count.year_to_date - cost;
    }

    public String DebugString() {
        return "Vin " + vin + " Make: " + make + " Model: " + model;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car other = (Car) o;
        return vin.equals(other.vin) && make.equals(other.make) && model.equals(other.model);
    }

}

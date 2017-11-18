package co.pextra.scenarios.SensitiveProductStorage2;


import co.pextra.model2.Entity;
import co.pextra.model2.IntrinsicContext;
import co.pextra.model2.Reading;

public class Location extends IntrinsicContext<Reading<LatLng>>{
    static  public  double degreesToRadians (double degrees) {
        return degrees * Math.PI / 180;
    }
    static public double earthRadius = (6.37814) * Math.pow(10, 6);

    public Location(String id, Entity bearer) {
        super(id, bearer);
    }
    @Override
    public String toString() {
        if (value == null) return "Bearer( " + bearer + " )" + " null";
        LatLng value = this.value.getValue();
        return "Bearer( " + bearer + " )" +  value;
    }

    static public double distance (Reading<LatLng> l1, Reading<LatLng> l2) {
        double lat1 = l1.getValue().getLatitude();
        double lng1 = l1.getValue().getLongitude();
        double lat2 = l2.getValue().getLatitude();
        double lng2 = l2.getValue().getLongitude();
        double dlat = degreesToRadians(lat1 - lat2);
        double dlng = degreesToRadians(lng1 - lng2);
        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.sin(dlng/2) * Math.sin(dlng/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c;
    }
    static public double distance (Location l1, Location l2) {
        return distance(l1.value, l2.value);
    }
}

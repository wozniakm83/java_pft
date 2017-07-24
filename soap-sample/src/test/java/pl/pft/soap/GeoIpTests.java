package pl.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("46.77.142.169");
        assertEquals(geoIP.getCountryCode(), "POL");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("46.77.142.xxx");
        assertEquals(geoIP.getCountryCode(), "POL");
    }
}

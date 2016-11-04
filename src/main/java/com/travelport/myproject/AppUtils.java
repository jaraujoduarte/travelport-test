package com.travelport.myproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.travelport.myproject.model.Airline;
import com.travelport.myproject.model.Airplane;
import com.travelport.myproject.model.City;
import com.travelport.myproject.model.Country;

/**
 * Class with utility methods and objects
 *
 */
public class AppUtils {
    private static Properties properties = new Properties();

    // Maps to load the information of the files in memory
    private static Map<String, City> cityMap;
    private static Map<String, Airline> airlineMap;
    private static Map<String, Airplane> airplaneMap;
    private static Map<String, Country> countryMap;

    /*
     * Interface to be used to declare callbacks for the parsing process of the
     * files with the vendor codes
     */
    private interface ICodedFileLineCallback {
        void process(List<String> line);
    }

    static {
        try {
            // Load properties
            properties.load(AppUtils.class.getClassLoader().getResourceAsStream("myproject.properties"));

            // Load cities
            cityMap = new HashMap<String, City>();
            iterateCodedFile("/input/FilesOfVendorCodes/RCTY.TXT", new ICodedFileLineCallback() {
                @Override
                public void process(List<String> line) {
                    City newCity = new City(line);
                    cityMap.put(newCity.getId(), newCity);
                }
            });

            // Load airlines
            airlineMap = new HashMap<String, Airline>();
            iterateCodedFile("/input/FilesOfVendorCodes/RAIR.TXT", new ICodedFileLineCallback() {
                @Override
                public void process(List<String> line) {
                    Airline newAirline = new Airline(line);
                    airlineMap.put(newAirline.getId(), newAirline);
                }
            });

            // Load airplanes
            airplaneMap = new HashMap<String, Airplane>();
            iterateCodedFile("/input/FilesOfVendorCodes/RAEQ.TXT", new ICodedFileLineCallback() {
                @Override
                public void process(List<String> line) {
                    Airplane newAirplane = new Airplane(line);
                    airplaneMap.put(newAirplane.getId(), newAirplane);
                }
            });

            // Load countries
            countryMap = new HashMap<String, Country>();
            iterateCodedFile("/input/FilesOfVendorCodes/RCNT.TXT", new ICodedFileLineCallback() {
                @Override
                public void process(List<String> line) {
                    Country newCountry = new Country(line);
                    countryMap.put(newCountry.getId(), newCountry);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Iterates over the file with the vendor codes parsing each line and making
     * it an object accessible through a key string
     * 
     * @param filePath
     *            The path to the file on the classpath
     * @param callback
     *            What to do with the line parsed as an array of strings
     */
    private static void iterateCodedFile(String filePath, ICodedFileLineCallback callback) {
        URL fileUrl = AppUtils.class.getResource(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileUrl.toURI())))) {
            String line;
            while ((line = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("\"([^\"]*)\"");
                Matcher matcher = pattern.matcher(line);
                List<String> matches = new ArrayList<String>();

                while (matcher.find()) {
                    // System.out.println(matcher.group(1));
                    matches.add(matcher.group(1));
                }

                callback.process(matches);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a country based on a code
     * 
     * @param code
     *            Code of the country
     * @return
     */
    public static Country getCountryFromCode(String code) {
        return countryMap.get(code);
    }

    /**
     * Gets an airline based on a code
     * 
     * @param code
     *            Code of the airline
     * @return
     */
    public static Airline getAirlineFromCode(String code) {
        return airlineMap.get(code);
    }

    /**
     * Gets an airplane based on a code
     * 
     * @param code
     *            Code of the airplane
     * @return
     */
    public static Airplane getAirplaneFromCode(String code) {
        return airplaneMap.get(code);
    }

    /**
     * Gets a city based on a code
     * 
     * @param code
     *            Code of the city
     * @return
     */
    public static City getCityFromCode(String code) {
        return cityMap.get(code);
    }

    /**
     * Returns a property value based on a given key
     * 
     * @param key
     *            Key of the property
     * @return
     */
    public static String getProperty(String key) {
        try {
            return properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to obtain the content of a file as a string
     * 
     * @param filePath
     *            Path on the classpath of the file
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String stringFromFile(String filePath) {
        try {
            return IOUtils.toString(AppUtils.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
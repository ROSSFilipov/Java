import com.sun.javafx.image.IntPixelGetter;
import javafx.util.Pair;

import java.util.*;

/**
 * Created by user on 11/12/2015.
 */
public class PopulationCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Long> populationData = new LinkedHashMap<>();
        Map<String, List<Pair<String, Integer>>> cityPopulation = new LinkedHashMap<>();

        while (!scanner.hasNext("report")){
            String[] currentData = scanner.nextLine().split("[|]+");

            String currentCountry = currentData[1];
            String currentCity = currentData[0];
            Integer currentPopulation = Integer.parseInt(currentData[2].trim());

            UpdateCountryPopulation(populationData, currentCountry, currentPopulation);
            UpdateCityList(cityPopulation, currentCountry, currentCity, currentPopulation);
        }

        Comparator<Map.Entry<String, Long>> customTotalPopulationComparator = new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };

        Comparator<Pair<String, Integer>> customeCityComparator = new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };

        List<Map.Entry<String, Long>> sortedCountries = new ArrayList<>(populationData.entrySet());
        Collections.sort(sortedCountries, customTotalPopulationComparator);

        for (Map.Entry<String, Long> currentCountry : sortedCountries){
            System.out.printf("%s (total population: %d)\n", currentCountry.getKey(), currentCountry.getValue());
            Collections.sort(cityPopulation.get(currentCountry.getKey()), customeCityComparator);
            for (Pair<String, Integer> currentCity : cityPopulation.get(currentCountry.getKey())){
                System.out.printf("=>%s: %d\n", currentCity.getKey(), currentCity.getValue());
            }
        }
    }

    private static void UpdateCityList(Map<String, List<Pair<String, Integer>>> cityPopulation, String currentCountry, String currentCity, Integer currentPopulation) {
        if (!cityPopulation.containsKey(currentCountry)){
            cityPopulation.put(currentCountry, new ArrayList<>());
        }

        cityPopulation.get(currentCountry).add(new Pair<String, Integer>(currentCity, currentPopulation));
    }

    private static void UpdateCountryPopulation(Map<String, Long> populationData, String currentCountry, Integer currentPopulation) {
        if (!populationData.containsKey(currentCountry)){
            populationData.put(currentCountry, 0l);
        }

        long countryPopulationValue = populationData.get(currentCountry) + currentPopulation;
        populationData.put(currentCountry, countryPopulationValue);
    }
}

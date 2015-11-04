import java.util.*;

/**
 * Created by user on 10/27/2015.
 */
public class OfficeStuff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();
        TreeMap<String, LinkedHashMap<String, Integer>> productData = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String[] currentLine = scanner.nextLine().split("[|\\-\\s]+");
            String currentCompany = currentLine[1];
            String currentProduct = currentLine[3];
            Integer currentAmount = Integer.parseInt(currentLine[2]);

            if (!productData.containsKey(currentCompany)){
                productData.put(currentCompany, new LinkedHashMap<>());
            }

            if (!productData.get(currentCompany).containsKey(currentProduct)){
                productData.get(currentCompany).put(currentProduct, 0);
            }

            int newValue = productData.get(currentCompany).get(currentProduct) + currentAmount;
            productData.get(currentCompany).put(currentProduct, newValue);
        }

        for (Map.Entry pair : productData.entrySet()) {
            System.out.printf("%s: ", pair.getKey());
            ArrayList<String> currentProducts = new ArrayList<>();
            for (Map.Entry innerPair : productData.get(pair.getKey()).entrySet()){
                currentProducts.add(String.format("%s-%d", innerPair.getKey(), innerPair.getValue()));
            }

            System.out.printf("%s\n", String.join(", ", currentProducts));
        }

    }
}

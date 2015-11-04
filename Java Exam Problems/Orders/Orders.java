import java.util.*;

/**
 * Created by user on 10/18/2015.
 */
public class Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, TreeMap<String, Integer>> productCollection = new LinkedHashMap<>();

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int amount = scanner.nextInt();
            String product = scanner.next();

            if (!productCollection.containsKey(product)){
                productCollection.put(product, new TreeMap<>());
                productCollection.get(product).put(name, 0);
            }

            if (!productCollection.get(product).containsKey(name)){
                productCollection.get(product).put(name, 0);
            }

            int oldAmount = productCollection.get(product).get(name);
            productCollection.get(product).put(name, oldAmount + amount);
        }

        for (String product : productCollection.keySet()) {
            System.out.print(product + ": ");
            TreeMap<String, Integer> amounts = productCollection.get(product);
            boolean first = true;
            for (Map.Entry<String, Integer> pair : amounts.entrySet()) {
                if (!first) {
                    System.out.print(", ");
                }
                first = false;
                String customer = pair.getKey();
                int amount = pair.getValue();
                System.out.print(customer + " " + amount);
            }
            System.out.println();
        }
    }
}

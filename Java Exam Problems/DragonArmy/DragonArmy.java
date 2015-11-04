import java.util.*;

/**
 * Created by user on 11/1/2015.
 */
class Dragon implements Comparable<Dragon>{
    private String type;
    private String name;
    private int damage;
    private int health;
    private int armor;

    public Dragon(){

    }

    public Dragon(String type, String name, int damage, int health, int armor){
        this.setType(type);
        this.setName(name);
        this.setDamage(damage);
        this.setHealth(health);
        this.setArmor(armor);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return this.armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Dragon o) {
        return this.name.compareTo(o.name);
    }
}

public class DragonArmy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Dragon>> dragonArmy = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] currentLine = scanner.nextLine().split("\\s+");
            String currentType = currentLine[0];
            String currentName = currentLine[1];
            int currentDamage = currentLine[2].equals("null") ? 45 : Integer.parseInt(currentLine[2]);
            int currentHealth = currentLine[3].equals("null") ? 250 : Integer.parseInt(currentLine[3]);
            int currentArmor = currentLine[4].equals("null") ? 10 : Integer.parseInt(currentLine[4]);

            if (!dragonArmy.containsKey(currentType)){
                dragonArmy.put(currentType, new ArrayList<>());
            }

            Dragon currentDragon = new Dragon(currentType, currentName, currentDamage, currentHealth, currentArmor);

            if (!ContainsDragon(dragonArmy.get(currentType), currentDragon)) {
                dragonArmy.get(currentType).add(currentDragon);
            } else {
                int index = GetDragonIndex(dragonArmy.get(currentType), currentDragon);
                dragonArmy.get(currentType).set(index, currentDragon);
            }
        }

        Comparator<Dragon> dragonComparator = new Comparator<Dragon>() {
            @Override
            public int compare(Dragon o1, Dragon o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        for (Map.Entry dragonType : dragonArmy.entrySet()){
            double averageDamage = 0;
            double averageHealth = 0;
            double averageArmor = 0;
            int currentDragonListSize = dragonArmy.get(dragonType.getKey()).size();
            for (int i = 0; i < currentDragonListSize; i++) {
                averageDamage += dragonArmy.get(dragonType.getKey()).get(i).getDamage();
                averageHealth += dragonArmy.get(dragonType.getKey()).get(i).getHealth();
                averageArmor += dragonArmy.get(dragonType.getKey()).get(i).getArmor();
            }

            averageDamage /= currentDragonListSize;
            averageHealth /= currentDragonListSize;
            averageArmor /= currentDragonListSize;

            List<Dragon> currentDragonList = new ArrayList<>(dragonArmy.get(dragonType.getKey()));
            Collections.sort(currentDragonList, dragonComparator);
            System.out.printf("%s::(%.2f/%.2f/%.2f)\n", dragonType.getKey(), averageDamage, averageHealth, averageArmor);
            for (Dragon dragon : currentDragonList) {
                System.out.printf("-%s -> damage: %d, health: %d, armor: %d\n",
                        dragon.getName(), dragon.getDamage(), dragon.getHealth(), dragon.getArmor());
            }
        }
    }

    private static int GetDragonIndex(List<Dragon> dragons, Dragon currentDragon) {
        int index = -1;
        for (int i = 0; i < dragons.size(); i++) {
            if (dragons.get(i).getName().equals(currentDragon.getName())){
                index = i;
                break;
            }
        }

        return index;
    }

    private static boolean ContainsDragon(List<Dragon> dragons, Dragon currentDragon) {
        for (int i = 0; i < dragons.size(); i++) {
            if (dragons.get(i).getName().equals(currentDragon.getName())){
                return true;
            }
        }

        return false;
    }
}

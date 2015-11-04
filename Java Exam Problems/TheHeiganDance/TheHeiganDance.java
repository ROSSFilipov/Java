import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 10/29/2015.
 */
public class TheHeiganDance {
    private static int chamberSize = 15;
    private static int playerX = chamberSize / 2;
    private static int playerY = chamberSize / 2;
    private static int playerHealth = 18500;
    private static Double HeiganHealth = 3000000d;
    private static boolean PlayerAlive = true;
    private static boolean HeiganAlive = true;
    private static int abilityRange = 1;
    private static int EruptionDamage = 6000;
    private static int CloudDamage = 3500;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double damageDone = Double.parseDouble(scanner.nextLine());

        int damageForNextRound = 0;
        String killAbility = "";
        while (HeiganAlive && PlayerAlive){
            String[] currentAttack = scanner.nextLine().split("\\s+");
            String currentAbility = currentAttack[0];
            int currentX = Integer.parseInt(currentAttack[1]);
            int currentY = Integer.parseInt(currentAttack[2]);

            HeiganHealth -= damageDone;
            playerHealth -= damageForNextRound;
            damageForNextRound = 0;
            if (HeiganHealth <= 0){
                HeiganAlive = false;
                if (playerHealth <= 0){
                    PlayerAlive = false;
                    killAbility = "Plague Cloud";
                }
                break;
            }

            if (playerHealth <= 0){
                PlayerAlive = false;
                killAbility = "Plague Cloud";
                break;
            }

            if (PlayerInRange(currentX, currentY)) {
                if (PlayerEscapes(currentX, currentY)){
                    continue;
                } else {
                    switch (currentAbility){
                        case "Eruption": playerHealth -= EruptionDamage; break;
                        case "Cloud": playerHealth -= CloudDamage; damageForNextRound = CloudDamage; break;
                    }

                    if (playerHealth <= 0){
                        PlayerAlive = false;
                        if (currentAbility.equals("Eruption")){
                            killAbility = "Eruption";
                        } else {
                            killAbility = "Plague cloud";
                        }
                    }
                }
            }
        }

        if (!HeiganAlive && !PlayerAlive){
            System.out.printf("Heigan: Defeated!\nPlayer: Killed by %s\nFinal position: %d, %d", killAbility, playerX, playerY);
        } else if (HeiganAlive){
            System.out.printf("Heigan: %.2f\nPlayer: Killed by %s\nFinal position: %d, %d", HeiganHealth, killAbility, playerX, playerY);
        } else {
            System.out.printf("Heigan: Defeated!\nPlayer: %d\nFinal position: %d, %d", playerHealth, playerX, playerY);
        }
    }

    private static boolean PlayerEscapes(int currentX, int currentY) {
        boolean moveUp = playerX - 1 < currentX - abilityRange && playerX - 1 >= 0;

        if (moveUp){
            playerX--;
            return true;
        }

        boolean moveRight = playerY + 1 > currentY + abilityRange && playerY + 1 < chamberSize;

        if (moveRight){
            playerY++;
            return true;
        }

        boolean moveDown = playerX + 1 > currentX + abilityRange && playerX + 1 < chamberSize;

        if (moveDown){
            playerX++;
            return true;
        }

        boolean moveLeft = playerY  - 1 < currentY - abilityRange && playerY - 1 >= 0;

        if (moveLeft){
            playerY--;
            return true;
        }

        return false;
    }

    private static boolean PlayerInRange(int currentX, int currentY) {
        if (currentX == playerX && currentY == playerY){
            return true;
        } else if (InsideAreaOfEffect(currentX, currentY)){
            return true;
        } else {
            return false;
        }
    }

    private static boolean InsideAreaOfEffect(int currentX, int currentY) {
        ArrayList<Pair<Integer, Integer>> areaOfEffect = new ArrayList<>();
        areaOfEffect.add(new Pair<>(currentX, currentY));
        areaOfEffect.add(new Pair<>(currentX - 1, currentY - 1));
        areaOfEffect.add(new Pair<>(currentX - 1, currentY));
        areaOfEffect.add(new Pair<>(currentX - 1, currentY + 1));
        areaOfEffect.add(new Pair<>(currentX, currentY - 1));
        areaOfEffect.add(new Pair<>(currentX, currentY + 1));
        areaOfEffect.add(new Pair<>(currentX + 1, currentY - 1));
        areaOfEffect.add(new Pair<>(currentX + 1, currentY));
        areaOfEffect.add(new Pair<>(currentX + 1, currentY - 1));

        for (int i = 0; i < areaOfEffect.size(); i++) {
            if (areaOfEffect.get(i).getKey() == playerX && areaOfEffect.get(i).getValue() == playerY){
                return true;
            }
        }

        return false;
    }
}

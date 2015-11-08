import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 11/8/2015.
 */
class Banshee {
    private int X;
    private int Y;
    private List<Banshee> communications;
    private boolean isVisited;

    public Banshee(int x, int y){
        this.setX(x);
        this.setY(y);
        this.communications = new ArrayList<>();
        this.setIsVisited(false);
    }

    public int getY() {
        return this.Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getX() {
        return this.X;
    }

    public void setX(int x) {
        X = x;
    }

    public List<Banshee> getCommunications(){
        return this.communications;
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    @Override
    public String toString(){
        return String.format("Banshee: %d %d", this.getX(), this.getY());
    }
}
public class Terran {
    private static int bansheeGroups = 0;
    private static double maxRange;
    public static void main(String[] args){
        Scanner scaner = new Scanner(System.in);

        maxRange = Double.parseDouble(scaner.nextLine());
        int numberOfWords = Integer.parseInt(scaner.nextLine());

        List<Pair<String, Integer>> codes = new ArrayList<>();
        for (int i = 0; i < numberOfWords; i++) {
            String[] currentLine = scaner.nextLine().split("\\s+");
            String currentWord = currentLine[0];
            Integer currentNumber = Integer.parseInt(currentLine[1]);
            codes.add(new Pair<String, Integer>(currentWord, currentNumber));
        }

        int numberOfBanshees = Integer.parseInt(scaner.nextLine());
        List<Banshee> bansheeList = new ArrayList<>();

        for (int i = 0; i < numberOfBanshees; i++) {
            String[] currentLine = scaner.nextLine().split("\\s+");
            int currentX = GetCoordinate(codes, currentLine[0]);
            int currentY = GetCoordinate(codes, currentLine[1]);
            bansheeList.add(new Banshee(currentX, currentY));
        }

        DistributeCommunications(bansheeList);

        List<List<Banshee>> allGroups = new ArrayList<>();

        for (int i = 0; i < bansheeList.size(); i++) {
            if (!bansheeList.get(i).isVisited()){
                List<Banshee> currentGroup = new ArrayList<>();
                AssembleBansheeGroup(currentGroup, bansheeList.get(i));
                allGroups.add(currentGroup);
            }
        }

        System.out.println("Bansee groups: " + allGroups.size());
        for (int i = 0; i < allGroups.size(); i++) {
            System.out.println(allGroups.get(i));
        }
    }

    private static int GetCoordinate(List<Pair<String, Integer>> codes, String currentCode) {
        int sum = 0;
        for (int i = 0; i < codes.size(); i++) {
            Pattern codePattern = Pattern.compile(codes.get(i).getKey());
            Matcher matcher = codePattern.matcher(currentCode);
            while (matcher.find()){
                sum += codes.get(i).getValue();
            }
        }

        return sum;
    }

    private static void DistributeCommunications(List<Banshee> bansheeList) {
        for (int i = 0; i < bansheeList.size(); i++) {
            Banshee currentBanshee = bansheeList.get(i);
            for (int j = 0; j < bansheeList.size(); j++) {
                if (i != j){
                    Banshee otherBanshee = bansheeList.get(j);
                    if (CanCommunicate(currentBanshee, otherBanshee)){
                        currentBanshee.getCommunications().add(otherBanshee);
                    }
                }
            }
        }
    }

    private static boolean CanCommunicate(Banshee currentBanshee, Banshee otherBanshee) {
        int distanceX = (currentBanshee.getX() - otherBanshee.getX()) * (currentBanshee.getX() - otherBanshee.getX());
        int distanceY = (currentBanshee.getY() - otherBanshee.getY()) * (currentBanshee.getY() - otherBanshee.getY());

        if (distanceX + distanceY <= maxRange){
            return true;
        } else {
            return false;
        }
    }

    private static void AssembleBansheeGroup(List<Banshee> currentGroup, Banshee banshee) {
        if (banshee.isVisited()){
            return;
        }

        currentGroup.add(banshee);
        banshee.setIsVisited(true);

        for (int i = 0; i < banshee.getCommunications().size(); i++) {
            AssembleBansheeGroup(currentGroup, banshee.getCommunications().get(i));
        }
    }
}

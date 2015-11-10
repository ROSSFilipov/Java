import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 11/8/2015.
 */
public class MostCommon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<String> firstName = new ArrayList<>();
        List<String> lastName = new ArrayList<>();
        List<String> yearOfBirth = new ArrayList<>();
        List<String> eyeColour = new ArrayList<>();
        List<String> hairColour = new ArrayList<>();
        List<String> height = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] currentLine = scanner.nextLine().split("[\\s,]+");
            firstName.add(currentLine[0]);
            lastName.add(currentLine[1]);
            yearOfBirth.add(currentLine[2]);
            eyeColour.add(currentLine[3]);
            hairColour.add(currentLine[4]);
            height.add(currentLine[5]);
        }

        System.out.println(GetMostCommon(firstName));
        System.out.println(GetMostCommon(lastName));
        System.out.println(GetMostCommon(yearOfBirth));
        System.out.println(GetMostCommon(eyeColour));
        System.out.println(GetMostCommon(hairColour));
        System.out.println(GetMostCommon(height));

        System.out.println();
    }

    private static String GetMostCommon(List<String> currentList) {
        int index = -1;
        int mostOccurances = 0;
        for (int i = 0; i < currentList.size(); i++) {
            int tempOccurances = 0;
            for (int j = 0; j < currentList.size(); j++) {
                if (currentList.get(i).equals(currentList.get(j))){
                    tempOccurances++;
                }
            }

            if (tempOccurances > mostOccurances){
                index = i;
                mostOccurances = tempOccurances;
            }
            
            if (tempOccurances == mostOccurances){
                if (currentList.get(index).compareToIgnoreCase(currentList.get(i)) > 0){
                    index = i;
                }
            }
        }

        return currentList.get(index);
    }
}

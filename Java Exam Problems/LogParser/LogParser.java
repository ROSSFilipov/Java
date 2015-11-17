import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 11/15/2015.
 */
public class LogParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> projects = new LinkedHashMap<>();
        Map<String, List<String>> criticals = new LinkedHashMap<>();
        Map<String, List<String>> warnings = new LinkedHashMap<>();

        Pattern inputPattern = Pattern.compile("\\[\"(.+?)\"\\]");
        while (scanner.hasNextLine()){
            String currentLine = scanner.nextLine();

            if (currentLine.equals("END")){
                break;
            }

            Matcher matcher = inputPattern.matcher(currentLine);
            String[] currentInfo = new String[3];

            int i = 0;
            while (matcher.find()){
                currentInfo[i] = matcher.group(1);
                i++;
            }

            String currentProject = currentInfo[0];
            String errorType = currentInfo[1];
            String errorMessage = currentInfo[2];

            if (!projects.containsKey(currentProject)){
                projects.put(currentProject, 0);
            }

            if (!criticals.containsKey(currentProject)){
                criticals.put(currentProject, new ArrayList<>());
            }

            if (!warnings.containsKey(currentProject)){
                warnings.put(currentProject, new ArrayList<>());
            }

            projects.put(currentProject, projects.get(currentProject) + 1);

            if (errorType.equals("Critical")){
                criticals.get(currentProject).add(errorMessage);
            } else {
                warnings.get(currentProject).add(errorMessage);
            }
        }

        Comparator<Map.Entry<String, Integer>> projectComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())){
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        };

        Comparator<String> errorComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               if (o1.length() == o2.length()){
                   return o1.compareTo(o2);
               } else {
                   int len1 = o1.length();
                   int len2 = o2.length();
                   return Integer.compare(len1, len2);
               }
            }
        };

        List<Map.Entry<String, Integer>> sortedProjects = new ArrayList<>(projects.entrySet());
        Collections.sort(sortedProjects, projectComparator);

        for (Map.Entry<String, Integer> currentProject : sortedProjects) {
            System.out.printf("%s:\nTotal Errors: %d\nCritical: %d\nWarnings: %d\n",
                    currentProject.getKey(), currentProject.getValue(), criticals.get(currentProject.getKey()).size(), warnings.get(currentProject.getKey()).size());
            List<String> currentCriticals = new ArrayList<>(criticals.get(currentProject.getKey()));

            Collections.sort(currentCriticals, errorComparator);
            System.out.println("Critical Messages:");
            if (currentCriticals.size() == 0) {
                System.out.println("--->None");
            } else {
                for (String currentError : currentCriticals) {
                    System.out.printf("--->%s\n", currentError);
                }
            }


            System.out.println("Warning Messages:");
            List<String> currentWarnings = new ArrayList<>(warnings.get(currentProject.getKey()));
            Collections.sort(currentWarnings, errorComparator);
            if (currentWarnings.size() == 0) {
                System.out.println("--->None");
            } else {
                for (String currentError : currentWarnings) {
                    System.out.printf("--->%s\n", currentError);
                }
            }
            System.out.println();
        }
    }
}

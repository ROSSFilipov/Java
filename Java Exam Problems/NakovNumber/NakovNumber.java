import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by user on 11/4/2015.
 */
class Scientist {
    private String name;
    private int nakovNumber;
    private List<Scientist> connections;
    private boolean isVisited;

    public Scientist(String name, int nakovNumber){
        this.setName(name);
        this.setNakovNumber(nakovNumber);
        this.connections = new ArrayList<>();
        this.setIsVisited(false);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNakovNumber() {
        return this.nakovNumber;
    }

    public void setNakovNumber(int nakovNumber) {
        this.nakovNumber = nakovNumber;
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public List<Scientist> getConnections() {
        return this.connections;
    }

    @Override
    public String toString(){
        return String.format("%s %d", this.getName(), this.getNakovNumber());
    }
}
public class NakovNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Pattern inputPattern = Pattern.compile("\"([A-Z\\s]+)\"");
        String input = scanner.nextLine();
        Matcher matcher = inputPattern.matcher(input);

        List<Scientist> scientistList = new ArrayList<>();

        while (matcher.find()){
                String[] publication = matcher.group(1).split("[, ]+");
                for (int i = 0; i < publication.length; i++) {
                    Scientist currentScientist = GetScientist(scientistList, publication[i]);
                    if (currentScientist.getName().equals("null")){
                        currentScientist = new Scientist(publication[i], -1);
                        scientistList.add(currentScientist);
                    }

                    for (int j = 0; j < publication.length; j++) {
                        if (i != j){
                            Scientist connectionScientist = GetScientist(scientistList, publication[j]);
                            if (connectionScientist.getName().equals("null")){
                                connectionScientist = new Scientist(publication[j], -1);
                                scientistList.add(connectionScientist);
                            }
                            currentScientist.getConnections().add(connectionScientist);
                        }
                    }
            }
        }

        for (int i = 0; i < scientistList.size(); i++) {
            if (scientistList.get(i).getName().equals("NAKOV")){
                scientistList.get(i).setNakovNumber(0);
                DistributeNakovNumbers(scientistList.get(i));
                break;
            }
        }

        Comparator<Scientist> customComparator = new Comparator<Scientist>() {
            @Override
            public int compare(Scientist o1, Scientist o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Collections.sort(scientistList, customComparator);
        scientistList.stream().forEach(s -> System.out.println(s));
    }

    private static Scientist GetScientist(List<Scientist> scientistList, String name) {
        for (int i = 0; i < scientistList.size(); i++) {
            if (scientistList.get(i).getName().equals(name)){
                return scientistList.get(i);
            }
        }

        return new Scientist("null", -1);
    }

    private static boolean ContainsScientist(List<Scientist> scientistList, String name) {
        for (int i = 0; i < scientistList.size(); i++) {
            if (scientistList.get(i).getName().equals(name)){
                return true;
            }
        }

        return false;
    }

    private static void DistributeNakovNumbers(Scientist currentScientist) {
        int lowestNakovNumber = currentScientist.getNakovNumber();
        for (int i = 0; i < currentScientist.getConnections().size(); i++) {
            if (currentScientist.getConnections().get(i).getNakovNumber() < lowestNakovNumber &&
                    lowestNakovNumber > 0 && currentScientist.getConnections().get(i).getNakovNumber() > 0){
                lowestNakovNumber = currentScientist.getConnections().get(i).getNakovNumber();
            }
        }

        if (currentScientist.isVisited()){
            return;
        } else {
            currentScientist.setIsVisited(true);
            for (int i = 0; i < currentScientist.getConnections().size(); i++) {
                if (currentScientist.getConnections().get(i).getNakovNumber() == -1) {
                    currentScientist.getConnections().get(i).setNakovNumber(lowestNakovNumber + 1);
                }
                DistributeNakovNumbers(currentScientist.getConnections().get(i));
            }
        }
    }
}

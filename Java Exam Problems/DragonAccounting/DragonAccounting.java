import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 11/1/2015.
 */
class EmployeeGroup{
    private double salary;
    private int dayHired;
    private long employeesCount;
    private int daysWorked;

    public EmployeeGroup(double salary, int dayHired, long employeesCount){
        this.setSalary(salary);
        this.setDayHired(dayHired);
        this.setEmployeesCount(employeesCount);
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDayHired() {
        return this.dayHired;
    }

    public void setDayHired(int dayHired) {
        this.dayHired = dayHired;
    }

    public long getEmployeesCount() {
        return this.employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }

    public int getDaysWorked() {
        return this.daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }
}
public class DragonAccounting {
    private static int daysCount = 1;
    private static BigDecimal capital;
    private static boolean hasBankrupted = false;
    private static double passiveIncrease = 0.006;
    private static int monthSize = 30;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.ROOT);
        capital = new BigDecimal(scanner.nextLine().trim());

        LinkedList<EmployeeGroup> employeesData = new LinkedList<>();

        Pattern inputPattern = Pattern.compile("([0-9]+);([0-9]+);([0-9.]+)[;]*(.+)*");
        while (!scanner.hasNext("END")){
            String currentLine = scanner.nextLine();
            Matcher matcher = inputPattern.matcher(currentLine);

            if (matcher.find()) {
                long numberOfEmployees = Long.parseLong(matcher.group(1));
                long firedEmployees = Long.parseLong(matcher.group(2));
                Double currentSalary = Double.parseDouble(matcher.group(3));
                String additionalEvents = matcher.group(4);

                HireEmployees(employeesData, numberOfEmployees, currentSalary);

                //Increasing days worked for all employees.
                for (EmployeeGroup currentGroup : employeesData){
                    int newDaysWorked = currentGroup.getDaysWorked() + 1;
                    currentGroup.setDaysWorked(newDaysWorked);
                }

                CheckForRaise(employeesData);
                GiveSalaries(employeesData);
                FireEmployees(employeesData, firedEmployees);
                if(additionalEvents != null) {
                    CheckForAdditionalEvents(additionalEvents);
                }
                CheckForBankruptcy();
            }

            if (hasBankrupted){
                break;
            }

            daysCount++;
        }

        String capitalFormatted = capital.abs().setScale(4, RoundingMode.DOWN).toPlainString();
        if (hasBankrupted){
            System.out.printf("BANKRUPTCY: %s", capitalFormatted);
        } else {
            int employeesCount = 0;
            for (int i = 0; i < employeesData.size(); i++) {
                employeesCount += employeesData.get(i).getEmployeesCount();
            }
            System.out.printf("%d %s", employeesCount, capitalFormatted);
        }
    }

    private static void HireEmployees(LinkedList<EmployeeGroup> employeesData, long numberOfEmployees, Double currentSalary) {
        EmployeeGroup currentGroup = new EmployeeGroup(currentSalary, daysCount, numberOfEmployees);
        currentGroup.setDaysWorked(0);
        employeesData.addLast(currentGroup);
    }

    private static void CheckForRaise(LinkedList<EmployeeGroup> employeesData) {
        for (EmployeeGroup currentGroup : employeesData){
            if (currentGroup.getDaysWorked() % 365 == 0){
                double increasedSalary = currentGroup.getSalary() + (currentGroup.getSalary() * passiveIncrease);
                currentGroup.setSalary(increasedSalary);
            }
        }
    }

    private static void GiveSalaries(LinkedList<EmployeeGroup> employeesData) {
        if (daysCount % monthSize == 0) {
            for (EmployeeGroup currentGroup : employeesData) {
                int tempDaysWorked = currentGroup.getDaysWorked();
                if (tempDaysWorked > monthSize){
                    tempDaysWorked = monthSize;
                }
                BigDecimal currentPayment = new BigDecimal(currentGroup.getSalary())
                        .divide(BigDecimal.valueOf(monthSize), 9, RoundingMode.UP)
                        .setScale(7, RoundingMode.DOWN)
                        .multiply(new BigDecimal(currentGroup.getEmployeesCount()))
                        .multiply(new BigDecimal(tempDaysWorked));
                capital = capital.subtract(currentPayment);
            }
        } else {
            return;
        }
    }

    private static void FireEmployees(LinkedList<EmployeeGroup> employeesData, long firedEmployees) {
        long employeesCount = firedEmployees;
        while (employeesCount > 0){
            if (employeesData.getFirst().getEmployeesCount() > employeesCount){
                employeesData.getFirst().setEmployeesCount(employeesData.getFirst().getEmployeesCount() - employeesCount);
                employeesCount = 0;
            } else {
                employeesCount -= employeesData.getFirst().getEmployeesCount();
                employeesData.removeFirst();
            }
        }
    }

    private static void CheckForAdditionalEvents(String additionalEvents) throws Exception {
        String[] eventSplit = additionalEvents.split("[;]+");
        for (int i = 0; i < eventSplit.length; i++) {
            String[] currentEvent = eventSplit[i].split(":+");
            switch (currentEvent[0]){
                case "Machines": capital = capital.subtract(new BigDecimal(currentEvent[1]));
                    break;
                case "Product development": capital = capital.add(new BigDecimal(currentEvent[1]));
                    break;
                case "Taxes": capital = capital.subtract(new BigDecimal(currentEvent[1]));
                    break;
                case "Previous years deficit": capital = capital.subtract(new BigDecimal(currentEvent[1]));
                    break;
                case "Unconditional funding": capital = capital.add(new BigDecimal(currentEvent[1]));
                    break;
                default: throw new Exception("That should never happen.");
            }
        }
    }

    private static void CheckForBankruptcy() {
        if (capital.compareTo(BigDecimal.ZERO) <= 0){
            hasBankrupted = true;
        }
    }
}
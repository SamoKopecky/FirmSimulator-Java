package com.vutbr.feec.console_interface;

import com.vutbr.feec.io.Database;
import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.JobType;
import com.vutbr.feec.firm.Firm;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConsoleInterface {
    private Database database = new Database();
    private Firm firm;
    private final char[] ALPHABET;
    private Map<Character, Option> options;
    private Map<Character, EmployeeType> employeeTypeMap;
    private Map<Character, JobType> jobTypeMap;
    private Scanner sc = new Scanner(System.in);
    private Option option;

    public ConsoleInterface(Firm firm) {
        ALPHABET = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        this.firm = firm;
        options = new HashMap<>();
        employeeTypeMap = new HashMap<>();
        jobTypeMap = new HashMap<>();
        options = generateMap(Option.values());
        employeeTypeMap = generateMap(EmployeeType.values());
        jobTypeMap = generateMap(JobType.values());
    }

    private <V> Map<Character, V> generateMap(V[] values) {
        Map<Character, V> generatedMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            generatedMap.put(ALPHABET[i], values[i]);
        }
        return generatedMap;
    }

    public void mainLoop() {
        while (true) {
            System.out.println(getOptions());
            option = scanChar(options);
            chooseWhatToDoNext();
        }
    }

    private <V> V scanChar(Map<Character, V> map) {
        char option;
        do {
            option = sc.nextLine().toUpperCase().charAt(0);
        } while (!map.keySet().contains(option));
        return map.get(option);
    }

    private void chooseWhatToDoNext() {
        switch (option) {
            case ADD_EMPLOYEE:
                System.out.print("meno : ");
                String firstName = sc.nextLine();
                System.out.print("priezvisko : ");
                String secondName = sc.nextLine();
                System.out.print("pozicia : \n");
                employeeTypeMap.forEach((key, value) -> System.out.println(key + " : " + value));
                EmployeeType employeeType = scanChar(employeeTypeMap);
                firm.addEmployee(firstName, secondName, employeeType);
                break;
            case ADD_JOB:
                jobTypeMap.forEach((key, value) -> System.out.println(key + " : " + value));
                JobType jobType = scanChar(jobTypeMap);
                System.out.print("dlzka prace : ");
                int duration = scanInt();
                boolean wasAddingSuccessful;
                wasAddingSuccessful = firm.addJob(jobType, duration, null);
                if (!wasAddingSuccessful) {
                    System.out.println("Nepodarilo sa rozdelit pracu");
                } else {
                    System.out.println("Pridanie prace bolo uspesne");
                }
                sc.nextLine();
                break;
            case DB_EXPORT:
                database.dbExport(firm, new File("test"));
                break;
            case DB_IMPORT:
                firm = database.dbImport(new File("test"));
                break;
            case PRINT_JOBS:
                for (EmployeeType type : EmployeeType.values()) {
                    System.out.println(type + ":");
                    for (Employee employee : firm.getListOfEmployees()) {
                        if (employee.getEmployeeType().equals(type)) {
                            System.out.println("ID : " + employee.getId() + " volne uvazky : " + employee.getContractLength());
                        }
                    }
                }
                sc.nextLine();
                break;
            case FIRE_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                int id = scanInt();
                if (firm.removeEmployee(id)) {
                    System.out.println("podarilo sa rozdelit pracu");
                } else {
                    System.out.println("nepodarilo sa rozdeilt pracu");
                }
                sc.nextLine();
                break;
            case SICK_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = scanInt();
                if (firm.makeEmployeeSick(id)) {
                    System.out.println("podarilo sa rozdelit pracu");
                } else {
                    System.out.println("nepodarilo sa rozdeilt pracu");
                }
                sc.nextLine();
                break;
            case PRINT_EMPLOYEES:
                char option;
                do {
                    System.out.println("Zoradit podla (A) priezviska lebo (B) id ?");
                    option = sc.nextLine().toUpperCase().charAt(0);
                } while (option != 'A' && option != 'B');
                switch (option) {
                    case 'A':
                        firm.getListOfEmployees().sort(Comparator.comparing(Employee::getSecondName));
                        break;
                    case 'B':
                        firm.getListOfEmployees().sort(Comparator.comparingInt(Employee::hashCode));
                        break;
                }
                for (Employee employee : firm.getListOfEmployees()) {
                    System.out.println(employee.toString());
                }
                sc.nextLine();
                firm.getListOfEmployees().sort(Comparator.comparingInt(Employee::hashCode));
                break;
            case HEALTHY_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = scanInt();
                firm.getElementByID(firm.getListOfEmployees(), id).setActive(true);
                break;
            case ACTIVATE_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = scanInt();
                Employee employee = firm.getElementByID(firm.getListOfEmployees(), id);
                jobTypeMap.forEach((key, value) -> {
                    if (employee.getCanDoTypeOfJobs().contains(value)) {
                        System.out.println(key + " : " + value);
                    }
                });
                jobType = scanChar(jobTypeMap);
                if (employee.getEmployeeType().equals(EmployeeType.ASSISTANT)) {
                    System.out.println("ID : ");
                    id = scanInt();
                    System.out.println(employee.action(jobType, firm.getElementByID(firm.getListOfEmployees(), id)));
                } else {
                    System.out.println(employee.action(jobType, employee));
                }
                sc.nextLine();
                break;
            case DECREASE_JOB_DURATION:
                System.out.println("Zadaj ID : ");
                id = scanInt();
                System.out.println("Zadaj pocet hodin o kolko chces znizit pracu : ");
                duration = scanInt();
                firm.getElementByID(firm.getListOfJobs(), id).decreaseJobDuration(duration);
                break;
            case SET_CONTRACT_DURATION:
                System.out.println("Zadaj ID : ");
                id = scanInt();
                sc.nextLine();
                System.out.println("Zadaj pocet hodin noveho uvazku : ");
                duration = scanInt();
                firm.getElementByID(firm.getListOfEmployees(), id).setMonthlyJobDuration(duration);
                break;
            case PRINT_MONTHLY_EXPENSES:
                System.out.println(firm.getMonthlyExpenses());
                sc.nextLine();
                break;
        }
    }

    private String getOptions() {
        clearConsole();
        String toReturn = "";
        for (Map.Entry<Character, Option> optionMap : options.entrySet()) {
            toReturn = toReturn.concat(optionMap.getKey() + " : " + optionMap.getValue().getDesc() + "\n");
        }
        return toReturn;
    }

    private int scanInt() {
        String numbers = "0123456789";
        String input;
        boolean validInput;
        do {
            validInput = true;
            input = sc.nextLine().toUpperCase();
            for (char c : input.toCharArray()) {
                if (!numbers.contains(String.valueOf(c))) {
                    if (input.charAt(input.length() - 1) == 'M') {
                        break;
                    }
                    validInput = false;
                }
            }
        } while (!validInput);
        if (input.charAt(input.length() - 1) == 'M') {
            input = input.substring(0, input.length() - 1);
            return Integer.valueOf(input) * 31;
        }
        return Integer.valueOf(input);

    }

    private void clearConsole() {
        String currentOs = System.getProperty("os.name").toLowerCase();

        if (currentOs.contains("linux")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } else if (currentOs.contains("windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                System.out.println("can't clean console");
            }
        } else {
            System.out.println("unknown OS");
        }
    }
}
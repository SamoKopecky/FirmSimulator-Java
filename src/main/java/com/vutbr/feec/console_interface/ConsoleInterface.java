package com.vutbr.feec.console_interface;

import com.vutbr.feec.IO.Database;
import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.JobType;
import com.vutbr.feec.firm.Firm;

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
        for (int i = 0; i < EmployeeType.values().length; i++) {
            employeeTypeMap.put(ALPHABET[i], EmployeeType.values()[i]);
        }
        for (int i = 0; i < Option.values().length; i++) {
            options.put(ALPHABET[i], Option.values()[i]);
        }
        for (int i = 0; i < JobType.values().length; i++) {
            jobTypeMap.put(ALPHABET[i], JobType.values()[i]);
        }
    }

    public void mainLoop() {
        char chosenOption;
        while (true) {
            System.out.println(getOptions());
            chosenOption = scanForOption();
            option = options.get(chosenOption);
            chooseWhatToDoNext();

        }
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
                EmployeeType employeeType = employeeTypeMap.get(sc.nextLine().toUpperCase().charAt(0));
                firm.addEmployee(firstName, secondName, employeeType);
                break;
            case ADD_JOB:
                jobTypeMap.forEach((key, value) -> System.out.println(key + " : " + value));
                JobType jobType = jobTypeMap.get(sc.nextLine().toUpperCase().charAt(0));

                System.out.print("dlzka prace : ");
                int duration = getNumOfDays(sc.nextLine());
                sc.nextLine();
                boolean wasAddingSuccessful;
                wasAddingSuccessful = firm.addJob(jobType, duration, null);
                if (!wasAddingSuccessful) {
                    System.out.println("Nepodarilo sa rozdelit pracu");
                }
                break;
            case DB_EXPORT:
                database.dbExport(null, null);
            case DB_IMPORT:
                database.dbImport();
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
                int id = Integer.valueOf(sc.nextLine());
                if (firm.removeEmployee(id)) {
                    System.out.println("podarilo sa rozdelit pracu");
                } else {
                    System.out.println("nepodarilo sa rozdeilt pracu");
                }
                sc.nextLine();
                break;
            case SICK_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = Integer.valueOf(sc.nextLine());
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
                    System.out.println("Zoradit podla (A)priezviska lebo (B)id ?");
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
                id = Integer.valueOf(sc.nextLine());
                firm.getListOfEmployees().get(id).setActive(true);
                break;
            case ACTIVATE_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = Integer.valueOf(sc.nextLine());
                sc.nextLine();
                Employee employee = firm.getListOfEmployees().get(id);

                for (Map.Entry<Character, JobType> map : jobTypeMap.entrySet()) {
                    if (employee.getCanDoTypeOfJobs().contains(map.getValue())) {
                        System.out.println(map.getKey() + " : " + map.getValue());
                    }
                }
                jobType = jobTypeMap.get(sc.nextLine().toUpperCase().charAt(0));
                if (employee.getEmployeeType().equals(EmployeeType.ASSISTANT)) {
                    System.out.println("ID : ");
                    id = Integer.valueOf(sc.nextLine());
                    sc.nextLine();
                    System.out.println(employee.action(jobType, firm.getListOfEmployees().get(id)));
                } else {
                    System.out.println(employee.action(jobType, employee));
                }
                sc.nextLine();
                break;
            case DECREASE_JOB_DURATION:
                System.out.println("Zadaj ID : ");
                id = Integer.valueOf(sc.nextLine());
                sc.nextLine();
                System.out.println("Zadaj pocet hodin o kolko chces znizit pracu : ");
                duration = getNumOfDays(sc.nextLine());
                sc.nextLine();
                firm.getListOfJobs().get(id).decreaseJobDuration(duration);
                break;
            case SET_CONTRACT_DURATION:
                System.out.println("Zadaj ID : ");
                id = Integer.valueOf(sc.nextLine());
                sc.nextLine();
                System.out.println("Zadaj pocet hodin noveho uvazku : ");
                duration = getNumOfDays(sc.nextLine());
                sc.nextLine();
                firm.getListOfEmployees().get(id).setMonthlyJobDuration(duration);
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

    private char scanForOption() {
        char charToReturn;
        do {

            charToReturn = sc.nextLine().toUpperCase().charAt(0);
        } while (!options.keySet().contains(charToReturn));
        return charToReturn;
    }

    private int getNumOfDays(String input) {
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
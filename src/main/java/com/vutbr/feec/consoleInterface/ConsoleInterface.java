package com.vutbr.feec.consoleInterface;

import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.Company;
import com.vutbr.feec.firm.JobType;
import com.vutbr.feec.io.Database;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInterface {
    private final char[] ALPHABET;
    private Database database = new Database();
    private Company company;
    private Map<Character, Option> options;
    private Map<Character, EmployeeType> employeeTypeMap;
    private Map<Character, JobType> jobTypeMap;
    private Scanner sc = new Scanner(System.in);
    private Option option;

    public ConsoleInterface(Company company) {
        ALPHABET = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        this.company = company;
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
            String line;
            do {
                line = sc.nextLine();
            } while (line.equals(""));
            option = line.toUpperCase().charAt(0);
        } while (!map.keySet().contains(option));
        return map.get(option);
    }

    private void chooseWhatToDoNext() {
        try {
            switch (option) {
                case ADD_EMPLOYEE:
                    addEmployee();
                    break;
                case ADD_JOB:
                    addJob();
                    break;
                case DB_EXPORT:
                    database.dbExport(company, new File(database.getCurrDate()));
                    break;
                case DB_IMPORT:
                    System.out.println("zvol si z ktoreho suboru chces importovat databasu");
                    company = database.dbImport(new File(sc.nextLine()));
                    break;
                case PRINT_JOBS:
                    printJobs();
                    break;
                case FIRE_EMPLOYEE:
                    fireEmployeeOrSickEmployee(true);
                    int id;
                    break;
                case SICK_EMPLOYEE:
                    fireEmployeeOrSickEmployee(false);
                    break;
                case PRINT_EMPLOYEES:
                    printEmployees();
                    break;
                case HEALTHY_EMPLOYEE:
                    System.out.println("Zadaj ID : ");
                    id = scanInt();
                    company.getObjectByID(company.getListOfEmployees(), id).setActive(true);
                    break;
                case ACTIVATE_EMPLOYEE:
                    activateEmployee();
                    break;
                case DECREASE_JOB_DURATION:
                    decreaseJobDuration();
                    break;
                case SET_CONTRACT_DURATION:
                    setContractDuration();
                    break;
                case PRINT_MONTHLY_EXPENSES:
                    System.out.println(company.getMonthlyExpenses());
                    sc.nextLine();
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Take id neexistuje");
        }
    }

    private void setContractDuration() {
        int id;
        int duration;
        System.out.println("Zadaj ID : ");
        id = scanInt();
        sc.nextLine();
        System.out.println("Zadaj pocet hodin noveho uvazku : ");
        duration = scanInt();
        company.getObjectByID(company.getListOfEmployees(), id).setMonthlyJobDuration(duration);
    }

    private void decreaseJobDuration() {
        int id;
        int duration;
        System.out.println("Zadaj ID : ");
        id = scanInt();
        System.out.println("Zadaj pocet hodin o kolko chces znizit pracu : ");
        duration = scanInt();
        company.getObjectByID(company.getListOfJobs(), id).decreaseJobDuration(duration, company);
    }

    private void activateEmployee() {
        int id;
        JobType jobType;
        System.out.println("Zadaj ID : ");
        id = scanInt();
        Employee employee = company.getObjectByID(company.getListOfEmployees(), id);
        jobTypeMap.forEach((key, value) -> {
            if (employee.getCanDoTypeOfJobs().contains(value)) {
                System.out.println(key + " : " + value.getDesc());
            }
        });
        jobType = scanChar(jobTypeMap);
        if (employee.getEmployeeType().equals(EmployeeType.ASSISTANT)) {
            System.out.println("ID : ");
            id = scanInt();
            System.out.println(employee.action(jobType, company.getObjectByID(company.getListOfEmployees(), id), employee));
        } else {
            System.out.println(employee.action(jobType, employee));
        }
        sc.nextLine();
    }

    private void printEmployees() {
        char option;
        do {
            System.out.println("Zoradit podla (A) priezviska lebo (B) id ?");
            String line;
            do {
                line = sc.nextLine();
            } while (line.isEmpty());
            option = line.toUpperCase().charAt(0);
        } while (option != 'A' && option != 'B');
        switch (option) {
            case 'A':
                company.getListOfEmployees().sort(Comparator.comparing(Employee::getSecondName));
                break;
            case 'B':
                company.getListOfEmployees().sort(Comparator.comparingInt(Employee::hashCode));
                break;
        }
        for (Employee employee : company.getListOfEmployees()) {
            System.out.println(employee.toString());
        }
        sc.nextLine();
        company.getListOfEmployees().sort(Comparator.comparingInt(Employee::hashCode));
    }

    private void fireEmployeeOrSickEmployee(boolean fireEmployee) {
        System.out.println("Zadaj ID : ");
        int id = scanInt();
        boolean success;
        if (fireEmployee) {
            success = company.removeEmployee(id);
        } else {
            success = company.makeEmployeeSick(id);
        }
        if (success) {
            System.out.println("podarilo sa rozdelit pracu");
        } else {
            System.out.println("nepodarilo sa rozdeilt pracu");
        }
        sc.nextLine();
    }

    private void printJobs() {
        for (EmployeeType type : EmployeeType.values()) {
            System.out.println(type.getDesc() + ":");
            for (Employee employee : company.getListOfEmployees()) {
                if (employee.getEmployeeType().equals(type)) {
                    System.out.println("ID : " + employee.getId() + " volne uvazky : " + employee.getContractLength());
                }
            }
        }
        sc.nextLine();
    }

    private void addJob() {
        jobTypeMap.forEach((key, value) -> System.out.println(key + " : " + value.getDesc()));
        JobType jobType = scanChar(jobTypeMap);
        System.out.print("dlzka prace : ");
        int duration = scanInt();
        boolean wasAddingSuccessful;
        wasAddingSuccessful = company.addJob(jobType, duration, null);
        if (!wasAddingSuccessful) {
            System.out.println("Nepodarilo sa rozdelit pracu");
        } else {
            System.out.println("Pridanie prace bolo uspesne");
        }
        sc.nextLine();
    }

    private void addEmployee() {
        System.out.print("meno : ");
        String firstName = sc.nextLine();
        System.out.print("priezvisko : ");
        String secondName = sc.nextLine();
        System.out.print("pozicia : \n");
        employeeTypeMap.forEach((key, value) -> System.out.println(key + " : " + value.getDesc()));
        EmployeeType employeeType = scanChar(employeeTypeMap);
        if (!company.addEmployee(firstName, secondName, employeeType))
            System.out.println("COE already exists");
    }

    private String getOptions() {
        clearConsole();
        StringBuilder sb = new StringBuilder();
        options.forEach((key, value) -> sb.append(key + " : " + value.getDesc() + "\n"));
        return sb.toString();
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
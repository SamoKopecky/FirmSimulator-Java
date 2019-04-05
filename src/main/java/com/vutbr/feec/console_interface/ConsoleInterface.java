package com.vutbr.feec.console_interface;

import com.vutbr.feec.IO.Database;
import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.Job;
import com.vutbr.feec.firm.TypeOfJob;
import com.vutbr.feec.firm.Firm;

import java.util.*;

public class ConsoleInterface {
    private Database database = new Database();
    private Firm firm;
    private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    private Map<Character, Option> options;
    private Map<Character, EmployeeType> employeeTypeMap;
    private Map<Character, TypeOfJob> jobTypeMap;
    private Scanner sc = new Scanner(System.in);
    private Option option;

    public ConsoleInterface(Firm firm) {
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
        for (int i = 0; i < TypeOfJob.values().length; i++) {
            jobTypeMap.put(ALPHABET[i], TypeOfJob.values()[i]);
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
                firm.addEmployee(firstName, secondName, employeeType.toString().toLowerCase());
                break;
            case ADD_JOB:
                jobTypeMap.forEach((key, value) -> System.out.println(key + " : " + value));
                TypeOfJob jobType = jobTypeMap.get(sc.nextLine().toUpperCase().charAt(0));
                System.out.print("dlzka prace : ");
                int duration = sc.nextLine().charAt(0);
                firm.addJob(jobType, duration, null);
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
                int id = sc.nextInt();
                firm.removeEmployee(id);
                sc.nextLine();
                break;
            case SICK_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = sc.nextInt();
                firm.makeEmployeeSick(id);
                sc.nextLine();
                break;
            case PRINT_EMPLOYEES:
                for (Employee employee : firm.getListOfEmployees()) {
                    System.out.println("\n\nID : " + employee.getId()
                            + "\n meno : " + employee.getFirstName()
                            + "\n priezvisko : " + employee.getSecondName()
                            + "\n pozicia : " + employee.getEmployeeType() + "\nList prac : ");
                    for (Job job : employee.getListOfJobs()) {
                        System.out.print(" ID : " + job.getId()
                                + "\n  pocet hodin : " + job.getDuration()
                                + "\n  typ prace : " + job.getTypeOfJob());
                    }
                }
                sc.nextLine();
                break;
            case HEALTHY_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = sc.nextInt();
                firm.getListOfEmployees().get(id).setActive(true);
                break;
            case ACTIVATE_EMPLOYEE:
                System.out.println("Zadaj ID : ");
                id = sc.nextInt();
                sc.nextLine();
                Employee employee = firm.getListOfEmployees().get(id);

                for (Map.Entry<Character, TypeOfJob> map : jobTypeMap.entrySet()) {
                    if (employee.getCanDoTypeOfJobs().contains(map.getValue())) {
                        System.out.println(map.getKey() + " : " + map.getValue());
                    }
                }
                jobType = jobTypeMap.get(sc.nextLine().toUpperCase().charAt(0));
                if (employee.getEmployeeType().equals(EmployeeType.ASSISTANT)) {
                    System.out.println("ID : ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println(employee.action(jobType, firm.getListOfEmployees().get(id)));
                } else {
                    System.out.println(employee.action(jobType, employee));
                }
                sc.nextLine();
                break;
            case DECREASE_JOB_DURATION:
                System.out.println("Zadaj ID : ");
                id = sc.nextInt();
                sc.nextLine();
                System.out.println("Zadaj pocet hodin o kolko chces znizit pracu : ");
                duration = sc.nextInt();
                sc.nextLine();
                firm.getListOfJobs().get(id).decreaseJobDuration(duration);
                break;
            case SET_CONTRACT_DURATION:
                System.out.println("Zadaj ID : ");
                id = sc.nextInt();
                sc.nextLine();
                System.out.println("Zadaj pocet hodin noveho uvazku : ");
                duration = sc.nextInt();
                sc.nextLine();
                firm.getListOfEmployees().get(id).setContractLength(duration);
                break;
            case PRINT_MONTHLY_EXPENSES:
                System.out.println(firm.getMonthlyExpenses());
                break;

        }
    }

    private String getOptions() {
        String toReturn = "";
        for (Map.Entry<Character, Option> optionMap : options.entrySet()) {
            toReturn = toReturn.concat(optionMap.getKey() + ": " + optionMap.getValue().getDesc() + "\n");
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
}
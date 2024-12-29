package com.taskmanager;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.BLUE + "Welcome to the task manager");
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        String fileName = "tasks.csv";
        String[][] tasks = loadTasks(fileName);

        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "add":
                    tasks = addTasks(tasks);
                    break;
                case "list":
                    listOfTasks(tasks);
                    break;
                case "edit":
                    int numberToEdit = getNumber();
                    tasks = editTasks(tasks, numberToEdit);
                    break;
                case "remove":
                    int numberToRemove = getNumber();
                    tasks = deleteTasks(tasks, numberToRemove);
                    break;
                case "exit":
                    saveTasks(tasks, fileName);
                    System.out.println(ConsoleColors.RED + "Exiting application" + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Please enter the correct option");
            }
            displayMenu();
        }

    }

    public static String[][] loadTasks(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println(ConsoleColors.RED + "The file " + fileName + " does not exist");
            return new String[0][0];
        }
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            String[] lines = content.toString().split("\n");
            String[][] tasks = new String[lines.length][];
            for (int i = 0; i < lines.length; i++) {
                tasks[i] = lines[i].split(",");
            }
            return tasks;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void displayMenu() {
        String[] options = {"add", "list", "edit", "remove", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select one of the following options" + ConsoleColors.RESET);

        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void listOfTasks(String[][] tasks) {
        if (tasks.length == 0) {
            System.out.println(ConsoleColors.RED + "No tasks found");
            return;
        }

        for (int i = 0; i < tasks.length; i++) {
            System.out.print((i + 1) + ": ");
            System.out.println(String.join(",", tasks[i]));
        }
    }

    public static String[][] addTasks(String[][] tasks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(ConsoleColors.YELLOW + "Please enter the description of task" + ConsoleColors.RESET);
        String description = scanner.nextLine();
        System.out.println(ConsoleColors.YELLOW + "Please enter the task due date" + ConsoleColors.RESET);
        String dueDate = scanner.nextLine();
        System.out.println(ConsoleColors.YELLOW + "Is your task important? (true/false)" + ConsoleColors.RESET);
        String isImportant = scanner.nextLine();
        isImportant = isImportant.toLowerCase();

        if (!isImportant.equalsIgnoreCase("true") && !isImportant.equalsIgnoreCase("false")) {
            System.out.println(ConsoleColors.RED + "Please enter the task true or false" + ConsoleColors.RESET);
        } else {
            tasks = Arrays.copyOf(tasks, tasks.length + 1);
            tasks[tasks.length - 1] = new String[3];
            tasks[tasks.length - 1][0] = description;
            tasks[tasks.length - 1][1] = dueDate;
            tasks[tasks.length - 1][2] = isImportant;
        }
        return tasks;
    }

    public static int getNumber() {

        Scanner scanner = new Scanner(System.in);
        int numberToRemove;

        while (true) {
            System.out.println("Enter the number of task");
            String input = scanner.nextLine();

            if (NumberUtils.isParsable(input)) {
                numberToRemove = Integer.parseInt(input);
                if (Integer.parseInt(input) < 0) {
                    System.out.println(ConsoleColors.RED + "Number must be greater than 0" + ConsoleColors.RESET);
                } else {
                    return numberToRemove;
                }
            } else {
                System.out.println(ConsoleColors.RED + "You must enter the number" + ConsoleColors.RESET);
            }
        }
    }

    public static String[][] deleteTasks(String[][] tasks, int numberToRemove) {
        if (numberToRemove < 1 || numberToRemove > tasks.length) {
            System.out.println(ConsoleColors.RED + "This task does not exist" + ConsoleColors.RESET);
            return tasks;
        }

        tasks = ArrayUtils.remove(tasks, numberToRemove - 1);
        System.out.println(ConsoleColors.PURPLE  + "Task was successfully deleted" + ConsoleColors.RESET);

        return tasks;
    }

    public static String[][] editTasks(String[][] tasks, int numberToEdit) {
        if (numberToEdit < 1 || numberToEdit > tasks.length) {
            System.out.println(ConsoleColors.RED + "This task does not exist" + ConsoleColors.RESET);
            return tasks;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            optionToEdit();
            String choice = scanner.nextLine();

            if ("exit".equals(choice)) {
                System.out.println(ConsoleColors.RED + "Exiting from editing" + ConsoleColors.RESET);
                break;
            }
            switch (choice) {
                case "description":
                    System.out.println("Enter the new description");
                    String newDescription = scanner.nextLine();
                    tasks[numberToEdit - 1][0] = newDescription;
                    System.out.println(ConsoleColors.GREEN + "Description updated" + ConsoleColors.RESET);
                    break;
                case "date":
                    System.out.println("Enter the new due date");
                    String newDueDate = scanner.nextLine();
                    tasks[numberToEdit - 1][1] = newDueDate;
                    System.out.println(ConsoleColors.GREEN + "Date updated" + ConsoleColors.RESET);
                    break;
                case "isImportant":
                    System.out.println("Enter the new importance of task");
                    String newImportance = scanner.nextLine();
                    newImportance = newImportance.toLowerCase();
                    if (!newImportance.equalsIgnoreCase("true") && !newImportance.equalsIgnoreCase("false")) {
                        System.out.println(ConsoleColors.RED + "Please enter the true or false" + ConsoleColors.RESET);
                    } else {
                        tasks[numberToEdit - 1][2] = newImportance;
                        System.out.println(ConsoleColors.GREEN + "Importance updated" + ConsoleColors.RESET);
                    }
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Please enter the correct option");
            }
        }
        return tasks;
    }

    public static void optionToEdit() {
        String[] optionsForEditing = {"description", "date", "isImportant"};
        System.out.println(ConsoleColors.BLUE + "Please select what exactly do you want to edit" + ConsoleColors.RESET);

        for (String optionToEdit : optionsForEditing) {
            System.out.println(optionToEdit);
        }
        System.out.println(ConsoleColors.RED + "exit" + ConsoleColors.RESET);
    }


    public static void saveTasks(String[][] tasks, String fileName) {
        File file = new File("tasks.csv");

        if (!file.exists()) {
            System.out.println(ConsoleColors.RED + "File not found " + fileName + ConsoleColors.RESET);
        }

        try (PrintWriter printwriter = new PrintWriter(file)) {
            for (String[] task : tasks) {
                String result = String.join(",", task);
                printwriter.println(result);
            }
            System.out.println(ConsoleColors.BLUE + "Tasks saved successfully" + ConsoleColors.RESET);

        } catch (FileNotFoundException e) {
            System.out.println(ConsoleColors.RED + "File not found " + e.getMessage() + ConsoleColors.RESET);
        }
    }
}
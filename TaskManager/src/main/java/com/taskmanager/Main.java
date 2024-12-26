package com.taskmanager;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String[][] tasks;

    public static void main(String[] args) {
        String[][] tasks = loadTasks();
        displayMenu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "add":
                    addTasks(tasks);
                    break;
                case "list":
                    listOfTasks(tasks);
                    break;
                case "remove":
                    deleteTasks();
                    break;
                case "exit":
                    saveTasks();
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Please enter the correct option");
            }
        }

    }

    public static String[][] loadTasks() {
        File file = new File("tasks.csv");
        int rowCount = 0;
        int columnCount = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                rowCount++;
                String line = scanner.nextLine();
                String[] task = line.split(",");
                columnCount = task.length;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
        }
        String[][] tasks = new String[rowCount][columnCount];

        try (Scanner scanner = new Scanner(file)) {
            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] task = line.split(",");
                for (int j = 0; j < columnCount; j++) {
                    tasks[index][j] = task[j];
                }
                index++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
        }
        return tasks;
    }

    public static void displayMenu() {
        String[] options = {"add", "list", "remove", "exit"};
        System.out.println(ConsoleColors.BLUE + "Welcome to the task manager");
        System.out.println(ConsoleColors.BLUE + "Please select one of the following options" + ConsoleColors.RESET);
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void listOfTasks(String[][] tasks) {
        int row = 0;
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(++row + ":");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j] + " " );
            }
            System.out.println();
        }
    }


    public static void addTasks(String[][] tasks) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.GREEN + "Please enter the description of task" + ConsoleColors.RESET);
        String description = scanner.nextLine();
        System.out.println(ConsoleColors.GREEN + "Please enter the task due date" + ConsoleColors.RESET);
        String dueDate = scanner.nextLine();
        System.out.println(ConsoleColors.GREEN + "Is your task important? (true/false)" + ConsoleColors.RESET);
        String isImportant = scanner.nextLine();

    }

    public static void deleteTasks() {

    }

    public static void saveTasks() {

    }


}
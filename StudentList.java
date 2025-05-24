import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StudentList {

    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.out.println("Error: No command provided. Usage: java StudentList [a|r|+name|?name|c]");
            return;
        }

        String command = args[0];

        if ((command.charAt(0) == '+' || command.charAt(0) == '?') && command.length() == 1) {
            System.out.println("Error: Command '" + command.charAt(0) + "' requires an additional argument.");
            return;
        }

        switch (command.charAt(0)) {
            case 'a':
                displayAllStudents();
                break;
            case 'r':
                displayRandomStudent();
                break;
            case 'c':
                countWordsInFile();
                break;
            case '?':
                searchStudentByName(command.substring(1));
                break;
            case '+':
                addNewStudent(command.substring(1));
                break;
            default:
                System.out.println("Error: Unknown command '" + command + "'.");
        }
    }

    private static void displayAllStudents() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String studentLine = reader.readLine();
            if (studentLine != null) {
                String[] studentNames = studentLine.split(",");
                for (String student : studentNames) {
                    System.out.println(student.trim());
                }
            }
        } catch (IOException exception) {
            System.out.println("Error reading student data.");
        }
        System.out.println("Data Loaded.");
    }

    private static void displayRandomStudent() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String studentLine =

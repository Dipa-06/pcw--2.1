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

        // Check command format
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
                countWords();
                break;
            case '?':
                searchStudent(command.substring(1));
                break;
            case '+':
                addStudent(command.substring(1));
                break;
            default:
                System.out.println("Error: Unknown command '" + command + "'.");
        }
    }

    private static void displayAllStudents() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                String[] students = line.split(",");
                for (String student : students) {
                    System.out.println(student.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student data.");
        }
        System.out.println("Data Loaded.");
    }

    private static void displayRandomStudent() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                String[] students = line.split(",");
                Random random = new Random();
                String student = students[random.nextInt(students.length)];
                System.out.println(student.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading student data.");
        }
        System.out.println("Data Loaded.");
    }

    private static void addStudent(String newStudent) {
        System.out.println("Loading data ...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss a");
            String timestamp = formatter.format(new Date());
            writer.write(", " + newStudent);
            writer.newLine();
            writer.write("List last updated on " + timestamp);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
        System.out.println("Data Loaded.");
    }

    private static void searchStudent(String name) {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                String[] students = line.split(",");
                for (String student : students) {
                    if (student.trim().equals(name)) {
                        System.out.println("We found it!");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student data.");
        }
        System.out.println("Data Loaded.");
    }

    private static void countWords() {
        System.out.println("Loading data ...");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                String[] words = line.trim().split("\\s+");
                System.out.println(words.length + " word(s) found");
            }
        } catch (IOException e) {
            System.out.println("Error reading student data.");
        }
        System.out.println("Data Loaded.");
    }
}

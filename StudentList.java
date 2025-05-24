import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {
        // Check if any arguments are provided
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            System.out.println("Usage: java StudentList [a|r|+Name|?Name|c]");
            return;
        }

        String option = args[0];

        // Option: Display all student names
        if (option.equals("a")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String line = reader.readLine();
                String[] studentNames = line.split(",");
                for (String student : studentNames) {
                    System.out.println(student.trim());
                }
            } catch (IOException e) {
                System.out.println("Error: Unable to read student data.");
            }
            System.out.println("Data Loaded.");
        }

        // Option: Display a random student
        else if (option.equals("r")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String[] studentNames = reader.readLine().split(",");
                Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt(studentNames.length);
                System.out.println(studentNames[randomIndex].trim());
            } catch (IOException e) {
                System.out.println("Error: Unable to read student data.");
            }
            System.out.println("Data Loaded.");
        }

        // Option: Add a new student name
        else if (option.startsWith("+")) {
            System.out.println("Loading data ...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
                String newStudent = option.substring(1);
                String timeStamp = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss a").format(new Date());
                writer.write(", " + newStudent + "\nList last updated on " + timeStamp);
            } catch (IOException e) {
                System.out.println("Error: Unable to update student list.");
            }
            System.out.println("Data Loaded.");
        }

        // Option: Search for a student
        else if (option.startsWith("?")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String[] studentNames = reader.readLine().split(",");
                String searchQuery = option.substring(1);
                for (String student : studentNames) {
                    if (student.trim().equals(searchQuery)) {
                        System.out.println("We found it!");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: Unable to search student data.");
            }
            System.out.println("Data Loaded.");
        }

        // Option: Count number of words in the file
        else if (option.equals("c")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String content = reader.readLine();
                if (content != null && !content.isEmpty()) {
                    String[] words = content.trim().split("\\s+");
                    System.out.println(words.length + " word(s) found");
                } else {
                    System.out.println("0 word(s) found");
                }
            } catch (IOException e) {
                System.out.println("Error: Unable to count words.");
            }
            System.out.println("Data Loaded.");
        }

        // Invalid argument handling
        else {
            System.out.println("Error: Invalid argument \"" + option + "\".");
            System.out.println("Valid options are: a, r, +Name, ?Name, c");
        }
    }
}

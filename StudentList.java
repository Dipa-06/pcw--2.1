import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            System.out.println("Usage: java StudentList [a|r|+Name|?Name|c]");
            return;
        }

        if (args[0].equals("a")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String line = reader.readLine();
                String[] students = line.split(",");
                for (String student : students) {
                    System.out.println(student.trim());
                }
            } catch (IOException e) {
                System.out.println("Error reading student data.");
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("r")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String[] students = reader.readLine().split(",");
                Random random = new Random();
                System.out.println(students[random.nextInt(students.length)].trim());
            } catch (IOException e) {
                System.out.println("Error reading student data.");
            }
            System.out.println("Data Loaded.");
        } else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
                String newStudent = args[0].substring(1);
                String timeStamp = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss a").format(new Date());
                writer.write(", " + newStudent + "\nList last updated on " + timeStamp);
            } catch (IOException e) {
                System.out.println("Error updating student data.");
            }
            System.out.println("Data Loaded.");
        } else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String[] students = reader.readLine().split(",");
                String search = args[0].substring(1);
                for (String student : students) {
                    if (student.trim().equals(search)) {
                        System.out.println("We found it!");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading student data.");
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("c")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
                String[] words = reader.readLine().trim().split("\\s+");
                System.out.println(words.length + " word(s) found");
            } catch (IOException e) {
                System.out.println("Error reading student data.");
            }
            System.out.println("Data Loaded.");
        } else {
            // STEP 6: Handle invalid arguments
            System.out.println("Error: Invalid argument \"" + args[0] + "\".");
            System.out.println("Valid options are: a, r, +Name, ?Name, c");
        }
    }
}

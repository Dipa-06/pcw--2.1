import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static void main(String[] args) {
        if (args[0].equals("a")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String line = reader.readLine();
                String[] students = line.split(",");
                for (String student : students) {
                    System.out.println(student.trim());
                }
            } catch (Exception e) {
                System.out.println("Data Loaded.");
            }
        } else if (args[0].equals("r")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String line = reader.readLine();
                String[] students = line.split(",");
                Random random = new Random();
                int index = random.nextInt(students.length);
                System.out.println(students[index].trim());
            } catch (Exception e) {
                System.out.println("Data Loaded.");
            }
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
                String newStudent = args[0].substring(1);
                Date now = new Date();
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss a");
                String timestamp = formatter.format(now);
                writer.write(", " + newStudent + "\nList last updated on " + timestamp);
                writer.close();
            } catch (Exception e) {
                System.out.println("Data Loaded.");
            }
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String line = reader.readLine();
                String[] students = line.split(",");
                String searchName = args[0].substring(1);
                for (String student : students) {
                    if (student.trim().equals(searchName)) {
                        System.out.println("We found it!");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Data Loaded.");
            }
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String line = reader.readLine();
                // STEP 5: Simplified logic for word count
                int wordCount = line.trim().split("\\s+").length;
                System.out.println(wordCount + " word(s) found");
            } catch (Exception e) {
                System.out.println("Data Loaded.");
            }
        }
    }
}

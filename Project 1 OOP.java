import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name = "";
        String id = "";
        int numCourses = 0;
        double[] marks = null;
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Enter Student Details");
            System.out.println("2. Enter Marks");
            System.out.println("3. Display Marks Info.");
            System.out.println("4. Display Student Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String rawName = input.nextLine().trim();
                    name = toTitleCase(rawName);

                    while (true) {
                        System.out.print("Enter ID Number: ");
                        id = input.next();
                        if (id.length() == 9 && id.startsWith("4")) {
                            break;
                        }
                        System.out.println("Invalid ID! Must start with 4 and be 9 digits.");
                    }

                    while (true) {
                        System.out.print("How many courses last semester? ");
                        numCourses = input.nextInt();
                        if (numCourses > 0 && numCourses <= 5) {
                            break;
                        }
                        System.out.println("Maximum number of courses should not exceed 5.");
                    }
                    marks = new double[numCourses];
                    break;

                case 2:
                    if (numCourses == 0) {
                        System.out.println("Please enter student details first!");
                    } else {
                        System.out.println("Enter Marks for " + numCourses + " subjects:");
                        for (int i = 0; i < numCourses; i++) {
                            while (true) {
                                System.out.print("Course " + (i + 1) + ": ");
                                double m = input.nextDouble();
                                if (m >= 0 && m <= 100) {
                                    marks[i] = m;
                                    break;
                                }
                                System.out.println("Mark must be between 0-100.");
                            }
                        }
                    }
                    break;

                case 3:
                case 4:
                    if (marks == null) {
                        System.out.println("No data available.");
                    } else {
                        displayAllInfo(name, id, marks);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = true;
        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextUpperCase = true;
                sb.append(c);
            } else if (nextUpperCase) {
                sb.append(Character.toUpperCase(c));
                nextUpperCase = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    public static void displayAllInfo(String name, String id, double[] marks) {
        double sum = 0, max = marks[0], min = marks[0];
        for (double m : marks) {
            sum += m;
            if (m > max) max = m;
            if (m < min) min = m;
        }
        double avg = sum / marks.length;

        System.out.println("\nName: " + name);
        System.out.println("KKU ID: " + id);
        System.out.println("Course\tMark\tP/F\tAvg: " + String.format("%.1f", avg));
        System.out.println("===========================");
        for (int i = 0; i < marks.length; i++) {
            String status = (marks[i] >= 60) ? "Pass" : "Fail";
            String tag = "--";
            if (marks[i] == max) tag = "Max";
            else if (marks[i] == min) tag = "Min";
            
            System.out.println((i + 1) + "\t" + (int)marks[i] + "\t" + status + "\t" + tag);
        }
    }
}

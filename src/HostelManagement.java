import java.io.*;
import java.util.Scanner;

class Hostel {
    int rno;
    String name;
    String location;
    String gender;
    int roomno;
}

public class HostelManagement {

    static int lower = 1, upper = 5;

    static void create() {
        Hostel h = new Hostel();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Roll no: ");
        h.rno = scanner.nextInt();
        System.out.print("Enter Name: ");
        h.name = scanner.next();
        System.out.print("Enter Location: ");
        h.location = scanner.next();
        System.out.print("Enter Gender: ");
        h.gender = scanner.next();
        h.roomno = getRoomNo((int) (Math.random() * (upper - lower + 1)) + lower);

        try (FileWriter fw = new FileWriter("hostel.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(h.rno + " " + h.name + " " + h.location + " " + h.gender + " " + h.roomno);
        } catch (IOException e) {
        }
    }

    static int getRoomNo(int roomno) {
        boolean allocated = false;
        try (BufferedReader br = new BufferedReader(new FileReader("hostel.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int existingRoomNo = Integer.parseInt(parts[4]);
                if (existingRoomNo == roomno) {
                    allocated = true;
                    break;
                }
            }
        } catch (IOException e) {
        }
        if (!allocated) {
            return roomno;
        } else {
            return getRoomNo((int) (Math.random() * (upper - lower + 1)) + lower);
        }
    }

    static void display() {
        try (BufferedReader br = new BufferedReader(new FileReader("hostel.txt"))) {
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.printf("%-10s%-20s%-15s%-10s%-5s\n", "RollNo", "Name", "Location", "Gender", "RoomNo");
            System.out.println("\n-----------------------------------------------------------------------");
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                System.out.printf("%-10s%-20s%-15s%-10s%-5s\n", parts[0], parts[1], parts[2], parts[3], parts[4]);
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i;
        String admin_user, admin_password;

        for (i = 1; i <= 1; i++) {
            System.out.println("--------------------------------------------------");
            System.out.println("WELCOME TO Dhaka Boarding");
            System.out.println("--------------------------------------------------");
            System.out.print("Enter User Name: ");
            admin_user = scanner.next();
            System.out.print("Enter Password: ");
            admin_password = scanner.next();

            if (!admin_user.equals("zk666") || !admin_password.equals("666")) {
                System.out.println("Wrong User Name or Password");
                i--;
            }
        }

        int ch;
        do {
            System.out.println("\n1. Allocate new room");
            System.out.println("2. Display");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            ch = scanner.nextInt();

            switch (ch) {
                case 1 -> create();
                case 2 -> display();
                case 0 -> System.out.println("\nThanks...");
            }

        } while (ch != 0);
    }
}
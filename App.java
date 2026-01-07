import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "family"));
        

        int choice;
        do {
            System.out.println("\n--- Room Booking System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> bookRoom(sc);
                case 3 -> cancelBooking(sc);
                case 4 -> viewBookings();
                case 5 -> System.out.println("Thank you!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println("Room " + room.getRoomNumber() + " (" + room.getType() + ")");
            }
        }
    }

    static void bookRoom(Scanner sc) {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNo && !room.isBooked()) {
                room.bookRoom();
                bookings.add(new Booking(name, room));
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void cancelBooking(Scanner sc) {
        System.out.print("Enter room number to cancel: ");
        int roomNo = sc.nextInt();

        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNo) {
                booking.getRoom().freeRoom();
                bookings.remove(booking);
                System.out.println("Booking cancelled.");
                return;
            }
        }
        System.out.println("Booking not found!");
    }

    static void viewBookings() {
        System.out.println("\nCurrent Bookings:");
        for (Booking booking : bookings) {
            System.out.println(
                "Customer: " + booking.getCustomerName() +
                ", Room: " + booking.getRoom().getRoomNumber()
            );
        }
    }
}

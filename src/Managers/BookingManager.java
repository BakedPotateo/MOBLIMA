package Managers;

import java.util.ArrayList;
import java.util.Scanner;

import Cinema.Cinema;
import Cinema.Seat;
import Cinema.SeatingLayout;
import Customer.Booking;
import Customer.Customer;
import Movies.Movie;
import Movies.Showtime;
import Tickets.Ticket;

public class BookingManager {
    // Attributes

    /*
     * instance checks whether BookingManager has been instantiated before. Static variable is the same between objects of the same class.
     */
    public static BookingManager instance = null;
    
    public Scanner sc = new Scanner(System.in);
    /*
     * Empty class constructor
     */
    private BookingManager(){}

    // Public methods

    /*
     * getInstance checks if BookingManager has been instantiated before. 
     * If no previous instance was created, it creates a new one, 
     * else it will use the original instance.
     */
    public static BookingManager getInstance()
    {
        if (instance == null)
            instance = new BookingManager(); // instance is a static variable
        return instance;
    }

    public void bookingMenu(){
        int choice = 0;
        Customer customer;
        String email;
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        
        while(choice != 2){
            System.out.println("------- BOOKING MENU -------\n"
                              +" 1. Book movie\n"
                              +" 2. Exit\n"
                              +"----------------------------");
            System.out.println("Please enter your choice:");

            /*
             * Check if input is an integer
             */
            while (!sc.hasNextInt()) {
            	System.out.println("Invalid input type. Please enter an integer value.");
        		sc.next(); // remove newline
            }

            choice = sc.nextInt();
            switch(choice){
                case 1:
                    bookings.add(this.bookMovie());
                    System.out.println("Please enter your email:");
                    while(!sc.hasNext()){
                        System.out.println("Invalid input type. Please try again. ");
                        sc.next(); //remove new line
                    }
                    email = sc.nextLine();
                    customer = CustomerManager.getInstance().getCustomer(email);
                    if(customer != null)
                        TransactionManager.getInstance().transaction(customer);
                    else{
                        customer = CustomerManager.getInstance().createCustomer(email, bookings);
                        TransactionManager.getInstance().transaction(customer);
                    }
                    break;
                case 2:
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Please enter an integer between 1-3.\n");
                    break;
            }
        }
    }

    private Booking bookMovie(){
        ArrayList<Movie> movies = MovieManager.getInstance().read();
        int movieChoice;
        while(true){
            for(int i = 0; i < movies.size(); i++){
                System.out.println((i+1) + ". " + movies.get(i).getTitle());
            }
            System.out.println("Please enter your movie choice:");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input type. Please enter an integer value.");
                sc.next(); // remove newline
            }
    
            movieChoice = sc.nextInt();
            if(movieChoice <= movies.size()) break;
        }
        
        Movie movie = movies.get(movieChoice - 1);

        ArrayList<Showtime> showtimes = movie.getShowtimes();
        for(int i = 0; i < showtimes.size(); i++){
            System.out.println((i+1) + ":");
            showtimes.get(i).makeString();
        }

        String showtimeChoice;
        while(true){
            System.out.println("Please choose a showtime:");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again.");
                sc.next(); // remove newline
            }
            showtimeChoice = sc.nextLine();
            if(ShowtimeManager.getInstance().isValidShowtimeID(movie, showtimeChoice)) break;
        }
        
        Showtime showtime = new Showtime();

        for(Showtime s : showtimes){
            if(s.getShowtimeID().equals(showtimeChoice)) showtime = s;
        }

        Cinema cinema = showtime.getCinema();
        SeatingLayout layout = cinema.getLayout();

        int seatChoice;
        ArrayList<Seat> seats = new ArrayList<Seat>();
        while(true){
            layout.printLayout();
            System.out.println("Please choose a seat:");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input type. Please enter an integer value.");
                sc.next(); // remove newline
            }
            seatChoice = sc.nextInt();
            layout.assignSeat(seatChoice);
            seats.add(layout.getSeat(seatChoice));
            System.out.println("Exit? (1 = exit, 2 = book another seat)");
            int exit;
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input type. Please enter an integer value.");
                sc.next(); // remove newline
            }

            exit = sc.nextInt();
            if (exit == 1) break;
        }

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (int i = 0; i < seats.size(); i++){
            Ticket t = new Ticket();
            t.setSeat(seats.get(i));
            tickets.add(t);
        }
        // choose ticket type

        tickets = TicketManager.getInstance().chooseTicketType(showtime, tickets);
        for(Ticket t : tickets)
            t.setMovieType(movie.getMovieType());

        // get ticket price
        ArrayList<Ticket> baseTickets = TicketManager.getInstance().read();

        for(Ticket t : tickets)
            for(Ticket bt : baseTickets){
                if(t.getTicketType().equals(bt.getTicketType()))
                    t.setTicketPrice(bt.getTicketPrice());
            }
        
        System.out.println("Ticket preview:");
        for(Ticket t : tickets)
            t.makeString();

        Booking booking = new Booking(movie, showtime, tickets);
        return booking;
    }
}

package Managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            sc.nextLine();

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
                    if(customer != null){
                        TransactionManager.getInstance().transaction(customer);
                    }
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
        ArrayList<Movie> movies = MovieManager.getInstance().getAvailableMovies();
        int movieChoice = -1;
        boolean loop = true;
        while(loop){
            for(int i = 0; i < movies.size(); i++){
                // System.out.println((i+1) + ". " + movies.get(i).getTitle());
                System.out.printf("%d. %s (%s)\n", (i+1), movies.get(i).getTitle(), movies.get(i).getShowingStatus());
            }
            System.out.println("Please enter your movie choice:");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input type. Please enter an integer value.");
                sc.next(); // remove newline
            }
    
            movieChoice = sc.nextInt() - 1;
            sc.nextLine();

            if(movieChoice < movies.size() && movieChoice >= 0) loop = false;
            if(movies.get(movieChoice).getShowingStatus().equals("END OF SHOWING")){
                System.out.println(movies.get(movieChoice).getTitle() + " is no longer showing. Please choose another movie.");
                loop = true;
            } else loop = false;
        }
        
        Movie movie = movies.get(movieChoice);

        ArrayList<Showtime> showtimes = movie.getShowtimes();
        for(int i = 0; i < showtimes.size(); i++){
            showtimes.get(i).makeString();
            System.out.println();
        }

        String showtimeChoice;
        while(true){
            System.out.println("Please choose a showtime:");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again.");
                sc.next(); // remove newline
            }
            showtimeChoice = sc.nextLine();
            if(ShowtimeManager.getInstance().showtimeIdExists(movie, showtimeChoice)) break;
        }
        
        Showtime showtime = new Showtime();

        for(Showtime s : showtimes){
            if(s.getShowtimeID().equals(showtimeChoice)) showtime = s;
        }

        Cinema cinema = showtime.getCinema();
        SeatingLayout layout = cinema.getLayout();

        String seatChoice;
        ArrayList<Seat> seats = new ArrayList<Seat>();
        while(true){
            layout.printLayout();
            System.out.println("Please choose a seat (e.g. A1):");
            while (!sc.hasNext()) {
                System.out.println("Invalid input type. Please try again");
                sc.next(); // remove newline
            }
            seatChoice = sc.nextLine();
            char row = Character.toUpperCase(seatChoice.charAt(0));
            char col = seatChoice.charAt(1);
            int colInt = (int) col - 49;
            int rowChoice = Integer.valueOf(row) - 65;
            int rowSize = layout.getRow();
            int seatChoiceInt = colInt + rowChoice*rowSize;
            layout.assignSeat(seatChoiceInt);
            seats.add(layout.getSeat(seatChoiceInt));
            seats.get(seats.size()-1).setSeatName(seatChoice.toUpperCase());
            System.out.println("Exit? (1 = exit, 2 = book another seat)");
            int exit;
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input type. Please enter an integer value.");
                sc.next(); // remove newline
            }

            exit = sc.nextInt();
            sc.nextLine();
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

        for(Ticket t : tickets){
            for(Ticket bt : baseTickets){
                if(t.getTicketType().equals(bt.getTicketType()))
                    t.setTicketPrice(bt.getTicketPrice());
            }
            if(cinema.getClassOfCinema().equals("Gold Class"))
                    t.setTicketPrice(t.getTicketPrice() + 5);
        }
        
        System.out.println("Ticket preview:");
        for(Ticket t : tickets)
            t.makeString();

        String transactionID = this.generateTransactionID(cinema.getId());

        Booking booking = new Booking(movie, showtime, tickets, transactionID);
        return booking;
    }

    private String generateTransactionID(String cinemaID){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
        LocalDateTime now = LocalDateTime.now();
        return cinemaID + now.format(dtf);

    }
}

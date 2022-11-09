package Managers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Movies.Showtime;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import Tickets.Ticket;

import utils.ProjectRootPathFinder;


public class TicketManager {
    public static TicketManager instance = null;
    private TicketManager(){
        this.createTicket("Senior Citizens", "2D");
        this.createTicket("Senior Citizens", "3D");
        this.createTicket("Students", "2D");
        this.createTicket("Students", "3D");
        this.createTicket("Standard", "2D");
        this.createTicket("Standard", "3D");
        this.createTicket("Weekends & Public holidays", "2D");
        this.createTicket("Weekends & Public holidays", "3D");
    }

    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Tickets/tickets.txt";
    Scanner sc = new Scanner(System.in);

    private String[] ticketTypes = {"Senior Citizens",
                                    "Students",
                                    "Standard",
                                    "Weekends & Public holidays"};

    private double[] ticketPrices =   {4.00, 7.00,  8.50, 11.00};
    private double[] ticketPrices3D = {9.00, 9.00, 11.00, 15.00};

    public static TicketManager getInstance() {
        if (instance == null) 
            instance = new TicketManager(); 
        return instance;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Ticket> read() {
        try {
            ObjectInputStream objectinputstream = new ObjectInputStream(new FileInputStream(FILE));
            ArrayList<Ticket> ticketList = (ArrayList<Ticket>) objectinputstream.readObject();
            objectinputstream.close();
            return ticketList;
        } catch (ClassNotFoundException | IOException e) {}
        return new ArrayList<Ticket>();
    }

    public void createTicket(String ticketType, String movieType) {
        double ticketPrice = 0;
        if(!movieType.equals("3D")){ // 2D movie
            for(int i = 0; i < ticketTypes.length; i++){
                if(ticketTypes[i].equals(ticketType)){
                    ticketPrice = ticketPrices[i];
                    break;
                }
            }
        }
        else{ // 3D movie
            for(int i = 0; i < ticketTypes.length; i++){
                if(ticketTypes[i].equals(ticketType)){
                    ticketPrice = ticketPrices3D[i];
                    break;
                }
            }
        }
        Ticket ticket = new Ticket(ticketType, movieType, ticketPrice);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            tickets = read();
        if (!ticketExists(tickets, ticketType, movieType)) try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            tickets.add(ticket);
            output.writeObject(tickets);
            output.flush();
            output.close();
        } catch (IOException e) {}
    }

    // ticketExists checks if ticket already exists, used to make sure duplicate ticket types are not created
    
    private boolean ticketExists(ArrayList<Ticket> tickets, String newTicketType, String newMovieType){
        for(Ticket ticket : tickets){
            if(ticket.getTicketType().equals(newTicketType) && ticket.getMovieType().equals(newMovieType)) 
                return true;
        }
        return false;
    }
    
    public ArrayList<Ticket> get2DMovies() {
        ArrayList<Ticket> tickets = read();
        ArrayList<Ticket> tickets2D = new ArrayList<Ticket>();
        for (Ticket ticket : tickets) {
            if (ticket.getMovieType().equals("2D")) {
                tickets2D.add(ticket);
            }
        }
        return tickets2D;
    }

    public ArrayList<Ticket> get3DMovies() {
        ArrayList<Ticket> tickets = read();
        ArrayList<Ticket> tickets3D = new ArrayList<Ticket>();
        for (Ticket ticket : tickets) {
            if (ticket.getMovieType().equals("3D")) {
                tickets3D.add(ticket);
            }
        }
        return tickets3D;
    }

    public void printAllTickets(){
        ArrayList<Ticket> tickets2D = this.get2DMovies();
        System.out.println();
        for (Ticket ticket : tickets2D) {
           ticket.makeString();
        }

        ArrayList<Ticket> tickets3D = this.get3DMovies();
        System.out.println();
        for (Ticket ticket : tickets3D) {
            ticket.makeString();
        }
    }
    public void editTicket(String ticketTypeChoice, boolean three_D){
        String movieType = (three_D) ? "3D" : "2D";
        System.out.println("Enter the new price:");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input type. Please enter an integer value.");
            sc.next(); // remove newline
        }
        double newPrice = sc.nextDouble();
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            tickets = read();
        for(Ticket ticket : tickets){
            if (ticket.getTicketType().equals(ticketTypeChoice) && ticket.getMovieType().equals(movieType)){
                ticket.setTicketPrice(newPrice);
            }
        }
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            output.writeObject(tickets);
            output.flush();
            output.close();
        } catch (IOException e) {}
    }

    public ArrayList<Ticket> chooseTicketType(Showtime showtime, ArrayList<Ticket> tickets){
        int choice;
        if(!isWeekend(showtime.getDateTime()) && !HolidayManager.getInstance().isPublicHoliday(showtime)){
            for(int i = 0; i < tickets.size(); i++){
                boolean loop = true;
                while (loop){
                    System.out.println("---------- TICKETS MENU ----------\n"
                                      +" 1. Ticket Type: Senior Citizens\n"
                                      +" 2. Ticket Type: Students\n"
                                      +" 3. Ticket Type: Standard\n"     
                                      +"----------------------------------");

                    System.out.println("Please enter your choice for ticket " + (i+1) +":");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input type. Please enter an integer value.");
                        sc.next(); // remove newline
                    }

                    choice = sc.nextInt();
                    switch(choice){
                        case 1:
                            tickets.get(i).setTicketType("Senior Citizens");
                            loop = false;
                            break;
                        case 2:
                            tickets.get(i).setTicketType("Students");
                            loop = false;
                            break;
                        case 3:
                            tickets.get(i).setTicketType("Standard");
                            loop = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }  
            }
        }
        else{
            System.out.println("All tickets set to weekend / public holiday prices.");
            for(Ticket t : tickets)
                t.setTicketType("Weekends & Public holidays");
        }

        return tickets;
    }

    public static boolean isWeekend(LocalDateTime ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }
}

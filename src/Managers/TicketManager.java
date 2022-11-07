package Managers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Tickets.Ticket;
import Movies.Movie;
import Cinema.Cinema;

import utils.ProjectRootPathFinder;


public class TicketManager {
    public static TicketManager instance = null;
    private TicketManager(){}

    public final static String FILE = ProjectRootPathFinder.findProjectRootPath() + "/Database/Tickets/tickets.txt";


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
        Ticket ticket = new Ticket(ticketType, movieType);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        File myFile = new File(FILE);
        if (myFile.exists()) 
            tickets = read();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
            tickets.add(ticket);
            output.writeObject(tickets);
            output.flush();
            output.close();
        } catch (IOException e) {}
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


}

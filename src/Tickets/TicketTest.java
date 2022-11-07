package Tickets;

import java.util.ArrayList;

import Managers.TicketManager;

public class TicketTest {
    public static void main(String[] args) {
        TicketManager manager = TicketManager.getInstance();
        manager.createTicket("Senior Citizens (Mon - Fri Before 6pm)", "3D");
        manager.createTicket("Students (Mon - Fri Before 6pm)", "2D");
        manager.createTicket("Students (Mon - Fri Before 6pm)", "3D");
        manager.createTicket("Mon - Wed", "2D");
        manager.createTicket("Mon - Wed", "3D");
        manager.createTicket("Thu", "2D");
        manager.createTicket("Thu", "3D");
        manager.createTicket("Fri (Before 6pm)", "2D");
        manager.createTicket("Fri (Before 6pm)", "3D");
        manager.createTicket("Sat & Sun", "2D");
        manager.createTicket("Sat & Sun", "3D");
        
        ArrayList<Ticket> tickets2D = manager.get2DMovies();
        for (Ticket ticket : tickets2D) {
           ticket.makeString();
        }

        ArrayList<Ticket> tickets3D = manager.get3DMovies();
        for (Ticket ticket : tickets3D) {
            ticket.makeString();
        }
    }
}

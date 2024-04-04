package com.example.ykhaledcustomersupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name="ticket", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID = 1;
    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      String action = request.getParameter("action");

      if (action == null){
          action = "list";
      }
      switch(action){
          case "createTicket" -> showTicketForm(request,response);
          case "view" -> viewTicket(request,response);
          case "download" -> downloadAttachment(request,response);
          default -> listTickets(request,response);

      }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null){
            action = "list";
        }
        switch(action){
            case "create" -> createTicket(request,response);
            default -> response.sendRedirect("ticket");

        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>List of Tickets:</h2>");
        out.println("<a href =\"ticket?action=createTicket\">Create Ticket</a><br><br>");
        if (ticketDB.isEmpty()){
            out.println("there are no tickets to view");
        }else{
            for (int id: ticketDB.keySet()) {
                Ticket ticket = ticketDB.get(id);
                out.println("Ticket Number:" + id);
                out.println(": <a href=\"ticket?action=view&ticketId=" + id +"\">");
                out.println("Customer Name: " + ticket.getCustomerName() + "</a><br>");
            }
        }
        out.println("</body></html>");
    }
    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(request.getParameter("customerName"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setBody(request.getParameter("body"));

        Part file = request.getPart("file1");
        if (file != null) {
            Attachment attachment = this.processAttachment(file);

            ticket.setAttachment(attachment);
        }

        int id;
        synchronized (this){
            id = this.TICKET_ID++;
            ticketDB.put(id,ticket);
        }

        response.sendRedirect("ticket?action=view&ticketId" + id);
    }

    private Attachment processAttachment(Part file) throws IOException {
        InputStream in = file.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int read;
        final byte [] bytes = new byte[1024];
        while((read = in.read(bytes)) != -1){
            out.write(bytes,0,read);
        }
        Attachment attachment = new Attachment();
        attachment.setName(file.getSubmittedFileName());
        attachment.setContents(out.toByteArray());

        return attachment;
    }
    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("ticketId");

        Ticket ticket = getTicket(idString, response);

        String attachmentName = request.getParameter("attachment");
        if (attachmentName == null) {
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
        }

        Attachment attachment = ticket.getAttachment();

        if (attachment == null) {
            response.sendRedirect("ticket?action=view&ticketId=" + idString);
            return;
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
        out.write(attachment.getContents());
    }


    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idString = request.getParameter("ticketId");
        Ticket ticket = getTicket(idString,response);

        PrintWriter out = response.getWriter();
        if (ticket != null) {
            out.println("<html><body><h2>Ticket " + idString + "</h2>");
            out.println("<h3>" + ticket.getCustomerName() + "</h3>");
            out.println("<p>Subject: " + ticket.getSubject() + "</p>");
            out.println("<p>" + ticket.getBody() + "</p>");
            if (ticket.hasAttachment()) {
                out.println("<a href=\"ticket?action=download&ticketId=" +
                        idString + "&attachment="+ticket.getAttachment().getName() +"\">" +
                        ticket.getAttachment().getName() + "</a><br><br>");
            }
            out.println("<a href=\"ticket\">Return to ticket list</a>");
            out.println("</body></html>");
        } else {
            // Handle case where ticket is null
            out.println("Ticket not found.");
        }
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<html><body><h2>Create a Ticket</h2>");
        out.println("<form method=\"POST\" action=\"ticket\" enctype=\"multipart/form-data\">");
        out.println("<input type=\"hidden\" name=\"action\" value=\"create\">");
        out.println("Customer Name:<br>");
        out.println("<input type=\"text\" name=\"customerName\"><br><br>");
        out.println("Subject:<br>");
        out.println("<input type=\"text\" name=\"subject\"><br><br>");
        out.println("Body:<br>");
        out.println("<textarea name=\"body\" rows=\"25\" cols=\"100\"></textarea><br><br>");
        out.println("<b>File</b><br>");
        out.println("<input type=\"file\" name=\"file1\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form></body></html>");
    }
    private Ticket getTicket(String idString,HttpServletResponse response) throws IOException {
        if (idString == null || idString.isEmpty()) {
            response.sendRedirect("ticket");
            return null;
        }
        try {
            int id = Integer.parseInt(idString);
            Ticket ticket = ticketDB.get(id);
            if (ticket == null) {
                response.sendRedirect("ticket");
                return null;
            }
            return ticket;
        } catch (Exception e) {
            response.sendRedirect("ticket");
            return null;
        }
    }
}

package com.example.ykhaledcustomersupport.site;

import com.example.ykhaledcustomersupport.entities.TicketEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class DefautTicketService implements TicketService{
    @Inject
    TicketRepository ticketRepo;
    @Inject
    AttachmentRepository attachmentRepo;


    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        ticketRepo.getAll().forEach(e ->list.add(this.convert(e)));
        return list;
    }

    @Override
    public Ticket getTicket(long id) {
        TicketEntity entity = ticketRepo.get(id);
        return entity == null ? null : this.convert(entity);
    }

    private Ticket convert(TicketEntity entity){
        Ticket ticket = new Ticket();
        ticket.setId(entity.getId());
        ticket.setCustomerName(entity.getCustomerName());
        ticket.setSubject(entity.getSubject());
        ticket.setBody(entity.getBody());
        // look up attachment in repo
        ticket.setAttachment(attachmentRepo.getByTicketId(entity.getId()));

        return ticket;
    }
    @Override
    @Transactional
    public void save(Ticket ticket) {
        //convert Ticket to TicketEntity
        TicketEntity entity = new TicketEntity();
        entity.setId(ticket.getId());
        entity.setCustomerName(ticket.getCustomerName());
        entity.setSubject(ticket.getSubject());
        entity.setBody(ticket.getBody());

        if (ticket.getId() < 1){
            ticketRepo.add(entity);
            // get the id from the entity to use in the Controller to actually view the ticket
            ticket.setId(entity.getId());
            // add image?
            if (ticket.hasAttachment()) {
                ticket.getAttachment().setTicketId(entity.getId());
                attachmentRepo.add(ticket.getAttachment());
            }
        }else { // just editing the ticket so update it on the DB
            ticketRepo.update(entity);
        }
    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);
    }
}

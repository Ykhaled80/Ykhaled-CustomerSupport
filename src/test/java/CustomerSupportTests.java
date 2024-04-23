import com.example.ykhaledcustomersupport.site.Attachment;
import com.example.ykhaledcustomersupport.site.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerSupportTests {

    @Test
    public void testAttachment() {
        Attachment attachment = new Attachment();
        attachment.setName("test.txt");
        byte[] contents = "Test attachment contents".getBytes();
        attachment.setContents(contents);

        assertEquals("test.txt", attachment.getName());
        assertArrayEquals(contents, attachment.getContents());
    }

    @Test
    public void testTicket() {
        Ticket ticket = new Ticket();
        ticket.setCustomerName("John Doe");
        ticket.setSubject("Test Subject");
        ticket.setBody("Test Body");

        assertEquals("John Doe", ticket.getCustomerName());
        assertEquals("Test Subject", ticket.getSubject());
        assertEquals("Test Body", ticket.getBody());

        // Add attachment
        Attachment attachment = new Attachment();
        attachment.setName("test.txt");
        byte[] contents = "Test attachment contents".getBytes();
        attachment.setContents(contents);
        ticket.addAttachment(attachment);

        assertEquals(1, ticket.getAttachments().size());
        assertEquals("test.txt", ticket.getAttachments().get(0).getName());
        assertArrayEquals(contents, ticket.getAttachments().get(0).getContents());
    }
}


package com.example.ykhaledcustomersupport.site;

import com.example.ykhaledcustomersupport.entities.Attachment;

public interface AttachmentRepository extends GenericRepository<Long, Attachment> {
    Attachment getByTicketId(Long ticketId);
}

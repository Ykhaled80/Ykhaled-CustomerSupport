package com.example.ykhaledcustomersupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements Serializable {
    private String customerName;
    private String subject;
    private String body;
    private List<Attachment> attachments;
    private Attachment attachment;
    public Ticket(String customerName, String subject, String body, List<Attachment> attachments, Attachment attachment) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
        this.attachment = attachment;
    }



    public Ticket() {
        this.attachments = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public boolean hasAttachment(){
        return attachment.getName().length() > 0 && attachment.getContents().length > 0;
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "customerName='" + customerName + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
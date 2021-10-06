package com.transaction.transaction.mail;

import javax.mail.internet.MimeMessage;

import com.transaction.transaction.proposal.Proposal;
import com.transaction.transaction.transaction.Transaction;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.mail.MailProperties;

@Component
public class EmailHelper {

    public static Boolean mailEnabled = false;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendTransactionCreationEnded(Transaction tran) {
        String subject = "TRANSACTION: Your transaction ID" + tran.getId() + "is succesfully written on the chain";
        String body = "<p>Dear user, " + tran.getId() + " just saved.</p> <p> Thank you </p>";
        sendMail(mailProperties.getUsername(), tran.getUser1(), subject, body);
        sendMail(mailProperties.getUsername(), tran.getUser2(), subject, body);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendTransactionCreationStarted(Transaction tran) {
        String subject = "TRANSACTION: sending your Proposal ID" + tran.getProposalID() + " to the chain";
        String body = "<p>Dear user, " + tran.getId() + " just created.</p> <p> It was the ProposalID"
                + tran.getProposalID() + " with this description: " + tran.getDescription()
                + "</p> <p>Now you only have to wait the confirmation mail. The chain is working to save your transaction. Thanks";
        sendMail(mailProperties.getUsername(), tran.getUser1(), subject, body);
        sendMail(mailProperties.getUsername(), tran.getUser2(), subject, body);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendProposalEmail(Proposal proposal) {
        String subject = "TRANSACTION: A new Proposal for you";
        String body = "<p>Dear user, " + proposal.getName() + " just created a Proposal for you with this description: "
                + proposal.getDescription() + "</p> <p>Please <a>confirm</a> or <a>decline</a></p> Thanks";

        sendMail(mailProperties.getUsername(), proposal.getRecipient(), subject, body);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendConfirmProposalCreationEmail(Proposal proposal) {

        String subject = "TRANSACTION: Proposal " + proposal.getId() + " created successfully";
        String body = "Dear " + proposal.getName() + ",\n you just created a Proposal (ID:" + proposal.getId()
                + ") with description: " + proposal.getDescription();
        sendMail(mailProperties.getUsername(), proposal.getEmail(), subject, body);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendAcceptedToCreatorMail(Proposal proposal) {
        String subject = "TRANSACTION: Proposal " + proposal.getId() + " accepted";
        String body = "Dear " + proposal.getName() + ",\n your Proposal (ID:" + proposal.getId()
                + ") with description: " + proposal.getDescription() + " has just been accepted!";
        sendMail(mailProperties.getUsername(), proposal.getEmail(), subject, body);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendAcceptedToRecipientMail(Proposal proposal) {
        String subject = "TRANSACTION: Proposal " + proposal.getId() + " accepted";
        String body = "Dear " + proposal.getRecipient() + ",\n you just accepted the Proposal (ID:" + proposal.getId()
                + ") with description: " + proposal.getDescription() + "! Thanks!";
        sendMail(mailProperties.getUsername(), proposal.getRecipient(), subject, body);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendDeclinedToCreatorMail(Proposal proposal) {
        String subject = "TRANSACTION: Proposal " + proposal.getId() + " denied";
        String body = "Dear " + proposal.getName() + ",\n your Proposal (ID:" + proposal.getId()
                + ") with description: " + proposal.getDescription() + " has just been declined!";
        sendMail(mailProperties.getUsername(), proposal.getEmail(), subject, body);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public void sendDeclinedToRecipientMail(Proposal proposal) {
        String subject = "TRANSACTION: Proposal " + proposal.getId() + " declined";
        String body = "Dear " + proposal.getRecipient() + ",\n you just declined the Proposal (ID:" + proposal.getId()
                + ") with description: " + proposal.getDescription() + "! Thanks for your collaboration!";
        sendMail(mailProperties.getUsername(), proposal.getRecipient(), subject, body);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    private void sendMail(String senderMail, String recipientMail, String subject, String body) {

        if (mailEnabled) {
            try {
                MimeMessage message = mailSender.createMimeMessage();

                MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
                messageHelper.setSubject(subject);
                messageHelper.setText(body, true);
                messageHelper.setFrom(senderMail);
                messageHelper.setTo(recipientMail);

                mailSender.send(message);

            } catch (MessagingException e) {
                System.err.println(e);
            }
        }

    }

}

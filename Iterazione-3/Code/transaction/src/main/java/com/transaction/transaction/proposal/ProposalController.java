package com.transaction.transaction.proposal;

import java.util.List;

import com.transaction.transaction.mail.EmailHelper;
import com.transaction.transaction.transaction.TransactionHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

//cross origin per risolvere localmente il problema dei CORS: in produzione da evitare assolutamente
@CrossOrigin
@RestController
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private EmailHelper emailHelper;

    @Autowired
    private TransactionHelper tranHelper;

    // POST
    @RequestMapping(method = RequestMethod.POST, value = "/proposals")
    public ResponseEntity<String> createProposal(@RequestBody Proposal prop) {
        try {
            proposalService.createProposal(prop);
            emailHelper.sendConfirmProposalCreationEmail(prop);
            emailHelper.sendProposalEmail(prop);

            return ResponseEntity.ok("Proposal successfully saved!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // GET
    // get all proposals
    @RequestMapping("/proposals")
    public List<Proposal> getAllProposals() {
        return proposalService.getAllProposals();
    }

    @GetMapping("proposals/{id}")
    public Proposal getProposalById(@PathVariable Integer id) {
        return proposalService.getProposalById(id);
    }

    // PUT
    @PutMapping("/proposals/{id}")
    public ResponseEntity<String> updateProposal(@PathVariable Integer id, @RequestBody Proposal proposal) {
        try {
            proposalService.updateProposal(id, proposal);
            return ResponseEntity.ok("Proposal successfully updated!");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidDataAccessApiUsageException ie) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    // Conferma o rifiuto di una proposta

    @PutMapping("/proposals/confirm/{id}")
    public ResponseEntity<String> confirmProposal(@PathVariable Integer id) {

        try {
            proposalService.confirmProposal(id);
            emailHelper.sendAcceptedToCreatorMail(proposalService.getProposalById(id));
            emailHelper.sendAcceptedToRecipientMail(proposalService.getProposalById(id));
            tranHelper.createTransaction(proposalService.getProposalById(id));

            return ResponseEntity.ok("Proposal successfully confirmed!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/proposals/decline/{id}")
    public ResponseEntity<String> declineProposal(@PathVariable Integer id) {

        try {
            proposalService.declineProposal(id);
            emailHelper.sendDeclinedToCreatorMail(proposalService.getProposalById(id));
            emailHelper.sendDeclinedToRecipientMail(proposalService.getProposalById(id));

            return ResponseEntity.ok("Proposal successfully declined!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    // DELETE
    @DeleteMapping("proposals/{id}")
    public ResponseEntity<String> deleteProposal(@PathVariable Integer id) {
        try {
            proposalService.deleteProposal(id);
            return ResponseEntity.ok("Proposal id: " + id + " deleted!");
        } catch (EmptyResultDataAccessException e) {
            String errMessage = "Error: you tried to delete a non-existent object!";
            System.err.println(errMessage);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errMessage);

        }
    }
}

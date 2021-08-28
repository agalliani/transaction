package com.transaction.transaction.proposal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    // POST
    @RequestMapping(method = RequestMethod.POST, value = "/proposals")
    public ResponseEntity<String> createProposal(@RequestBody Proposal prop) {
        try {
            if (prop.getName() == null) {
                String message = "Error: Null name found! The field name cannot be null!";
                System.err.println(message);
                throw new Exception(message);
            }
            proposalService.createProposal(prop);

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

    // PUT
    @PutMapping("/proposals/{id}")
    public ResponseEntity<String> updateProposal(@PathVariable Integer id, @RequestBody Proposal proposal) {
        proposalService.updateProposal(id, proposal);

        return ResponseEntity.ok("Proposal successfully updated!");
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

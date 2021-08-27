package com.transaction.transaction.proposal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void createProposal(@RequestBody Proposal prop) {
        proposalService.createProposal(prop);
    }

    //GET
    //get all proposals
    @RequestMapping("/proposals")
    public List<Proposal> getAllProposals(){
        return proposalService.getAllProposals();
    }
}

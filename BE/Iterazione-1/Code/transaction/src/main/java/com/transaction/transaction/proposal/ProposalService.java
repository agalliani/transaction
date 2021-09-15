package com.transaction.transaction.proposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepositoryIf proposalRepository;

    public List<Proposal> getAllProposals() {
        // salvo i dati restituiti dalla query
        List<Proposal> proposals = new ArrayList<>();

        proposalRepository.findAll().forEach(proposals::add);
        return proposals;
    }

    public void createProposal(Proposal prop) throws Exception {
        if (!proposalRepository.existsById(prop.getId())) {
            proposalRepository.save(prop);
        } else {
            String message = "A proposal with this ID already exists!";
            throw new Exception(message);
        }

    }

    public void updateProposal(Integer id, Proposal prop) throws Exception {
        //non existing elements can't be updated
        if (proposalRepository.existsById(prop.getId())) {
            // overwrite the existing element in the DB. The id is unique
            proposalRepository.save(prop);
        }
        else{
            String message = "Non existing elements can't be updated!";
            throw new Exception(message);
        }

    }

    public void deleteProposal(Integer id) {
        proposalRepository.deleteById(id);
    }

}

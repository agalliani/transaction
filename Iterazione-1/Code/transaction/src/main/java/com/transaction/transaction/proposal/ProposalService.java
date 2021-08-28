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

    public void createProposal(Proposal prop) {
        proposalRepository.save(prop);
    }

    public void updateProposal(Integer id, Proposal prop) {
        // overwrite the existing element in the DB. The id is unique
        proposalRepository.save(prop);
    }

    public void deleteProposal(Integer id) {
        proposalRepository.deleteById(id);
    }

}

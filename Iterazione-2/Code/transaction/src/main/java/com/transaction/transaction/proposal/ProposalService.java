package com.transaction.transaction.proposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

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

            if (prop.getName() == null || prop.getEmail() == null || prop.getId() == null
                    || prop.getRecipient() == null) {
                String message = "Error: Null field found! The fields ID, name, email, recipient cannot be null!";
                System.err.println(message);
                throw new Exception(message);
            }

            // force new proposal to be pending
            if (prop.getStatus() != StatusProposal.PENDING) {
                prop.setStatus(StatusProposal.PENDING);
            }

            proposalRepository.save(prop);
        } else {
            String message = "A proposal with this ID already exists!";
            throw new Exception(message);
        }

    }

    public void updateProposal(Integer id, Proposal prop) throws Exception {
        // non existing elements can't be updated
        if (proposalRepository.existsById(prop.getId())) {

            StatusProposal actualStatus = getProposalById(id).getStatus();
            //update cannot change the status of a proposal. It can be done only by direct confirmation/decline of the recipient
            if(actualStatus.equals(StatusProposal.PENDING) && prop.getStatus()!= StatusProposal.PENDING){
                String msg = "Invalid access: update cannot change the status of a proposal. It can be done only by direct confirmation/decline of the recipient";
                throw new InvalidDataAccessApiUsageException(msg);
            }

            // overwrite the existing element in the DB. The id is unique
            proposalRepository.save(prop);
        } else {
            String message = "Non existing elements can't be updated!";
            throw new NotFoundException(message);
        }

    }

    public void deleteProposal(Integer id) {
        proposalRepository.deleteById(id);
    }

    public Proposal getProposalById(Integer id) {
        return proposalRepository.findById(id).get();
    }

    public void confirmProposal(Integer id) throws Exception {
        Proposal updated = getProposalById(id);
        updated.setStatus(StatusProposal.CONFIRMED);
        updateProposal(id, updated);

    }

    public void declineProposal(Integer id) throws Exception {
        Proposal updated = getProposalById(id);
        updated.setStatus(StatusProposal.DECLINED);
        updateProposal(id, updated);
    }

}

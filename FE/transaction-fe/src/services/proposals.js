import api from "./axiosInstance";

function getAllProposals() {
  return api.get("proposals");
}

function addNewProposal(id, name, description, email, recipient) {
  const status = "PENDING";

  return api.post("proposals", {
    id,
    name,
    description,
    email,
    recipient,
    status,
  });
}

function deleteProposal(id) {
  return api.delete("proposal/" + id);
}

export default {
  getAllProposals,
  addNewProposal,
  deleteProposal,
};

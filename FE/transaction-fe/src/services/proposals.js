import api from "./axiosInstance";

function getAllProposals() {
  return api.get("proposals");
}

export default {
  getAllProposals,
};

import api from "./axiosInstance";

function getAllTransactions() {
  return api.get("transactions");
}

export default {
  getAllTransactions,
};

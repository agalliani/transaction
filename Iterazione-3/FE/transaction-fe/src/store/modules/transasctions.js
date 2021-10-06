import transactionService from "../../services/transactions";

const initialState = () => ({ courses: [] });

const state = inistialState();

const mutations = {
  resetState(state) {
    Object.assign(state, initialState());
  },

  GET_ALL_TANSACTIONS(state, transactions) {
    state.transactions = [];
    for (const transaction of transactions) {
      const { id, name, description, email, recipient, status } = transaction;
    }
  },
};

const actions = {
  async getAllTransactions({ commit }) {
    try {
      const { data } = await transactionService.getAllTransactions();
      commit("GET_ALL_TANSACTIONS", data);
    } catch (err) {
      await handleAxiosError(`Errore durante il caricamento dei corsi`, err);
      return null;
    }
  },
};

const getters = {};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters,
};

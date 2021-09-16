import proposalService from "../../services/proposals";

const initialState = () => ({ courses: [] });

const state = inistialState();

const mutations = {
  resetState(state) {
    Object.assign(state, initialState());
  },

  GET_ALL_PROPOSALS(state, proposals) {
    state.proposals = [];
    for (const proposal of proposals) {
      const { id, name, description, email, recipient, status } = proposal;
    }
  },
};

const actions = {
  async getAllProposals({ commit }) {
    try {
      const { data } = await proposalService.getAllProposals();
      commit("GET_ALL_PROPOSALS", data);
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

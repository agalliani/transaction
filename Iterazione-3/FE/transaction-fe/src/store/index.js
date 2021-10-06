Vue.use(Vuex);

const actions = {
  resetState({ commit }) {
    commit("proposals/resetState", {}, { root: true });
    commit("transactions/resetState", {}, { root: true });
  },
};

export default new Vuex.Store({
  modules: {
    proposals,
    transactions
  },
  actions,
});

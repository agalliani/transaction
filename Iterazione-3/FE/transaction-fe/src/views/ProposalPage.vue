<template>
  <v-container fluid class="fill-height">
    <v-col>
      <v-row align="center">
        <v-col cols="3">
          <v-navigation-drawer permanent>
            <v-list-item-group nav active-class="border">
              <v-list-item v-on:click="type = true">
                <v-list-item-icon>
                  <v-icon> mdi-plus-circle-outline </v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>Create</v-list-item-title>
                </v-list-item-content>
              </v-list-item>

              <v-list-item
                v-on:click="
                  type = false;
                  getData();
                "
              >
                <v-list-item-icon>
                  <v-icon>mdi-clipboard-list-outline</v-icon>
                </v-list-item-icon>

                <v-list-item-content>
                  <v-list-item-title>View All</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-navigation-drawer>
        </v-col>

        <v-col cols="8">
          <div v-if="type == false">
            <div v-for="proposal in proposal_list" v-bind:key="proposal.id">
              <AllProposals :proposal="proposal" />
            </div>
          </div>

          <div v-else><ProposalCreation /></div>
        </v-col>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import proposals from "../services/proposals";
import AllProposals from "../components/AllProposals";
import ProposalCreation from "../components/ProposalCreation";

export default {
  name: "Proposal",
  components: {
    AllProposals,
    ProposalCreation,
  },
  data() {
    return {
      proposal_list: [],
      type: false,
    };
  },
  methods: {
    async getData() {
      try {
        const response = await proposals.getAllProposals();
        this.proposal_list = response.data;
        console.log(this.proposal_list);
      } catch (error) {
        console.log(error);
      }
    },
  },
  created() {
    this.getData();
  },
};
</script>
<style scoped>
.border {
  border: 1px red;
}
</style>

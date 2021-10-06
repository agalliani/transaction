<template>
  <v-card class="my-4" elevated>
    <v-card-title>{{ proposal.name }}</v-card-title>
    <v-card-subtitle
      >{{ proposal.email }} >>> {{ proposal.recipient }}</v-card-subtitle
    >
    <v-card-text>
      {{ proposal.description }}
    </v-card-text>
    <v-card-actions>
      <v-col> Status: {{ proposal.status }} </v-col>
      <v-col
        ><v-btn
          v-on:click="
            deleteProposal(proposal.id);
            console.log(proposal.id);
          "
        >
          Delete
        </v-btn></v-col
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import proposals from "../services/proposals";

export default {
  name: "AllProposal",
  props: {
    proposal: {
      type: Object,
      required: true,
    },
  },
  methods: {
    async deleteProposal(id) {
      try {
        const res = await proposals.deleteProposal(id);
        console.log(res);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

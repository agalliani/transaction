<template>
  <div>
    FORM DI INSERIMENTO DATI
    <v-btn v-on:click="submit()"> SUBMIT </v-btn>
    {{ message }}
  </div>
</template>

<script>
import proposals from "../services/proposals";

export default {
  data() {
    return {
      message: "",

      id: 15187,
      name: "AAAAA",
      description: "Questa è una breve descrizione.",
      email: "andrea.galliani.29@gmail.com",
      recipient: "a.galliani@studenti.unibg.it",
    };
  },
  methods: {
    async submit() {
      try {
        const res = await proposals.addNewProposal(
          this.id,
          this.name,
          this.description,
          this.email,
          this.recipient
        );
        this.message = res.data;
      } catch (error) {
          this.message = "Errore caricamento dati!"
        console.log(error);
      }
    },
  },
};
</script>

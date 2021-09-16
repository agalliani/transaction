<template>
  <v-container fluid>
    <v-col>
      <v-row>
        <v-col cols="3">
         
            <v-navigation-drawer permanent>
              <v-list dense nav>
                <v-list-item v-for="item in items" :key="item.title" link>
                  <v-list-item-icon>
                    <v-icon>{{ item.icon }}</v-icon>
                  </v-list-item-icon>

                  <v-list-item-content>
                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-navigation-drawer>
         
        </v-col>

        <v-col cols = "8">
          <v-card
            v-for="props in proposal_list"
            v-bind:key="props.id"
            class="my-4"
            elevated
          >
            <v-card-title>{{ props.name }}</v-card-title>
            <v-card-subtitle
              >{{ props.email }} >>> {{ props.recipient }}</v-card-subtitle
            >
            <v-card-text>
              <p>{{ props.description }}</p>
              <p>Status: {{ props.status }}</p>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import proposals from "../services/proposals";

export default {
  name: "Proposal",
  data() {
    return {
      proposal_list: [],
      items: [
        { title: "Create", icon: "mdi-view-dashboard" },
        { title: "View All", icon: "mdi-image" },
        { title: "Search for ID", icon: "mdi-help-box" },
      ],
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

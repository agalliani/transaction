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

        <v-col cols="8">
          <v-card
            v-for="trans in transaction_list"
            v-bind:key="trans.id"
            class="my-4"
            elevated
          >
            <v-card-title>{{ trans.user1 }} >>> {{ trans.user2 }}</v-card-title>
            <v-card-subtitle>{{ trans.info }} </v-card-subtitle>
            <v-card-text>
              {{ trans.description }}
            </v-card-text>
            <v-card-actions >
              <v-col> TransactionID: {{ trans.id }} </v-col>

            
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
  </v-container>
</template>

<script>
import transactions from "../services/transactions";

export default {
  name: "Transaction",
  data() {
    return {
      transaction_list: [],
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
        const response = await transactions.getAllTransactions();
        this.transaction_list = response.data;
        console.log(this.transaction_list);
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

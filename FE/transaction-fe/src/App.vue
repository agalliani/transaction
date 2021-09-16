<template>
  <v-app>
    <div>
      <v-app-bar color="red" dense>
        <v-toolbar-title>
          <v-btn @click="$router.push('/')" outlined> Home </v-btn>
        </v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn class="ma-3" outlined @click="$router.push('/proposals')">Proposals</v-btn>
        <v-btn class="ma-3" outlined @click="$router.push('/transactions')">Transactions</v-btn>
      </v-app-bar>
    </div>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    //
  }),
};
</script>

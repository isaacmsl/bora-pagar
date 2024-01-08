<script setup lang="ts">
import UserListItem from '@/components/UserListItem.vue';
import UserProfile from '@/components/UserProfile.vue';
import { useAuthStore } from '@/stores/auth';
import type { AppUser } from '@/types/AppUser';
import { onMounted, type Ref } from 'vue';
import { computed } from 'vue';
import { VTextField } from 'vuetify/components';
import { VIcon } from 'vuetify/components';
import axios from 'axios';
import { ref } from 'vue';

const auth = useAuthStore();
const loggedIn = computed(() => auth.loggedIn());
const users : Ref<AppUser[]> = ref([]);
const inputUsername : Ref<string> = ref('');

async function searchByUsername(){
  const response = await axios.get(`http://localhost:8080/users?partialUsername=${inputUsername.value}`);
  users.value = response.data;
}

onMounted(async () => {
  auth.getCredentialFromLocalStorage();
  searchByUsername();
});


</script>

<template>
  <div class="container">
    <header class="pageHeader">
      <div class="pageInfo">
        <h1>Buscar amigo </h1>
        <v-text-field 
          bg-color="#202333" 
          density="comfortable" 
          variant="solo-filled"
          v-model="inputUsername"
          @keyup="searchByUsername"
        >
          <template v-slot:append-inner>
            <v-icon icon="mdi-magnify" size="30"/>
          </template>
        </v-text-field>
      </div>

      <div>
        <GoogleLogin
          class="googleLogin"
          v-if="!loggedIn"
          :callback="auth.googleLoginCallback"
        />
        <UserProfile v-if="loggedIn"/>
      </div>
    </header>

    <ul class="userList">
      <UserListItem v-for="user in users" :user="user" :key="user.username"/>
    </ul>
  </div>
</template>

<style scoped>
.container {
  padding: 1rem 0;
  width: 80%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
}
.pageInfo {
  width: 40rem;
}

.pageInfo h1 {
  font-weight: bold;
  font-size: 6rem;
}

.pageHeader {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.userList {
  list-style-type: none;
  min-height: 70vh;
  background-color: #202333;
  /*background-color: #151721;*/

  border: 1px solid #363C40;
  border-radius: 0.5rem;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  grid-auto-rows: 10rem;
  padding: 3rem;
  gap: 2rem;
}

.searchButton {
  background-color: #2591D7;
  height: 100%;
  aspect-ratio: 1;
  border-radius: 0.5rem;
}

.searchButton:hover {
  background-color: #1f75af;
}
</style>
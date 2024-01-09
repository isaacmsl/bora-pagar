<script setup lang="ts">
import UserListItem from '@/components/UserListItem.vue';
import UserMenu from '@/components/UserMenu.vue';
import type { AppUser } from '@/types/AppUser';
import { onMounted, type Ref } from 'vue';
import { VTextField } from 'vuetify/components';
import { VIcon } from 'vuetify/components';
import { ref } from 'vue';
import { UserService } from '@/services/UserService';
import { debounce } from '@/util/debounce';

const api = new UserService();
const users : Ref<AppUser[]> = ref([]);
const inputUsername : Ref<string> = ref('');

async function fetchUsers() {
  users.value = await api.searchUsersByUsername(inputUsername.value);
}

const handleUsernameInput = debounce(fetchUsers)

onMounted(() => {
  fetchUsers();
});
</script>

<template>
  <div class="container">
    <header class="pageHeader">
      <div class="pageInfo">
        <h1>Buscar amigo</h1>
        <v-text-field
          bg-color="#202333"
          density="comfortable"
          variant="solo-filled"
          v-model="inputUsername"
          @keyup="handleUsernameInput"
        >
          <template v-slot:append-inner>
            <v-icon icon="mdi-magnify" size="30"/>
          </template>
        </v-text-field>
      </div>

      <UserMenu />
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
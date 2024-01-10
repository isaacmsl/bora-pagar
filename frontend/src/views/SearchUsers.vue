<script setup lang="ts">
import UserListItem from '@/components/UserListItem.vue';
import UserMenu from '@/components/UserMenu.vue';
import type { AppUser } from '@/types/AppUser';
import { onMounted, type Ref } from 'vue';
import { VTextField, VPagination } from 'vuetify/components';
import { VIcon } from 'vuetify/components';
import { ref } from 'vue';
import { UserService } from '@/services/UserService';
import { debounce } from '@/util/debounce';
import { useAuthStore } from '@/stores/auth';

const auth = useAuthStore();
const api = new UserService();
const users: Ref<AppUser[]> = ref([]);
const inputUsername: Ref<string> = ref('');
const qntPages = ref(0);
const page = ref(1);
const qntVisiblePages = 6;

async function fetchPage() {
  const pageUser = await api.searchUsersByUsername(page.value - 1, inputUsername.value);
  users.value = pageUser.content;
  qntPages.value = pageUser.totalPages;
}

const handleUsernameInput = debounce(fetchPage);

onMounted(() => {
  fetchPage();
});
</script>

<template>
  <div class="container">
    <header class="pageHeader">
      <div class="pageInfo">
        <h1>Buscar amigo</h1>
        <v-text-field bg-color="#202333" density="comfortable" variant="solo-filled" v-model="inputUsername"
          @keyup="handleUsernameInput" placeholder="Buscar por username">
          <template v-slot:append-inner>
            <v-icon icon="mdi-magnify" size="30" />
          </template>
        </v-text-field>
      </div>

      <UserMenu />
    </header>

    <ul class="userList">
      <UserListItem v-for="user in users" :user="user" :key="user.username" />
    </ul>
    <v-pagination :length="qntPages" v-model="page" color="primary" @click="fetchPage" :total-visible="qntVisiblePages"
      :disabled="!auth.loggedIn()"></v-pagination>
  </div>
</template>

<style scoped>
.container {
  padding: 0.75rem 0;
  width: 80%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
}

.pageInfo {
  width: 400px;
  display: grid;
  gap: 1.25rem;
}

.pageInfo h1 {
  font-weight: bold;
  font-size: 2.5rem;
}

.pageHeader {
  display: flex;
  flex-wrap: wrap-reverse;
  gap: 1.25rem;
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
  grid-auto-rows: 6rem;
  padding: 2rem;
  gap: 1.25rem;
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

@media only screen and (max-width: 600px) {
  .userList {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  }
}

@media only screen and (max-width: 1280px) {
  .pageHeader {
    justify-content: flex-end;
  }

  .pageInfo {
    width: 100%;
  }
}
</style>
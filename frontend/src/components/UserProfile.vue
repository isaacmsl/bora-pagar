<script setup lang="ts">
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';
import { computed } from 'vue';
import { VIcon } from 'vuetify/components';

const auth = useAuthStore();
const user = computed(() => auth.user);

onMounted(() => {
  auth.getCredentialFromLocalStorage();
});
</script>

<template>
  <nav class="userProfile">
    <section>
      <h2>{{ user?.given_name }}</h2>
      <button @click="auth.logout">
        <v-icon icon="mdi-logout"/> Sair
      </button>
    </section>
    <img :src="user?.picture"/>
  </nav>
</template>

<style scoped>
.userProfile {
  display: flex;
  align-items: center;
  gap: 2rem;
}

.userProfile h2 {
  font-size: 3rem;
}

.userProfile section {
  display: grid;
  place-items: end;
  gap: .4rem;
}

.userProfile button {
  font-weight: bold;
  color: var(--app-strong-blue);
  background-color: var(--app-blue-soft);
  padding: 1rem;
  font-size: 1.6rem;
  border-radius: 1rem;
}

.userProfile button:hover {
  background-color: var(--app-strong-blue);
  color: white;
}

.userProfile img {
  border: .5rem solid white;
  border-radius: 50%;
  width: 80px;
  height: 80px;
}
</style>
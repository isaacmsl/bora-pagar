<script setup lang="ts">
import router from '@/router';
import { useAuthStore } from '@/stores/auth';
import { navigateToSubjectsOfUserGoogleId } from '@/util/navigation';
import { onMounted, ref } from 'vue';
import { computed } from 'vue';
import { VIcon, VNavigationDrawer, VList, VListItem, VDivider} from 'vuetify/components';
import { useDisplay } from 'vuetify';

const auth = useAuthStore();
const user = computed(() => auth.user);
const drawer = ref(false);
const { xs } = useDisplay();

onMounted(() => {
  auth.getCredentialFromLocalStorage();
});
</script>

<template>
  <div v-if="xs">
    <button @click="drawer = !drawer">
      <v-icon icon="mdi-menu" />
    </button>
    <v-navigation-drawer 
      v-model="drawer"
      location="right"
      temporary
    >

    <v-list>
      <v-list-item :append-avatar="user?.picture" :title="user?.name" :subtitle="user?.email" />
    </v-list>

    <v-divider />

    <v-list nav>
      <v-list-item to="/" color="primary" append-icon="mdi-home" title="Início"/>
      <v-list-item :to="`/subjects-user/${user?.sub}`"  color="primary" append-icon="mdi-check-circle" title="Pagarei" />
      <v-list-item to="/search-users" color="primary" append-icon="mdi-account-search" title="Amigos"/>
      <v-list-item append-icon="mdi-logout" color="primary" title="Sair" @click="auth.logout"/>
    </v-list>

    </v-navigation-drawer>
  </div>
  <nav v-else class="userProfile">
    <section>
      <h2>{{ user?.given_name }}</h2>
      <nav>
        <button @click="router.push('/')">
          <v-icon icon="mdi-home" /> Início
        </button>
        <button @click="router.push(`/subjects-user/${user?.sub}`)">
          <v-icon icon="mdi-check-circle" /> Pagarei
        </button>
        <button @click="router.push('/search-users')">
          <v-icon icon="mdi-account-search" /> Amigos
        </button>
        <button @click="auth.logout">
          <v-icon icon="mdi-logout" /> Sair
        </button>
      </nav>
    </section>
    <img
      title="Ver suas disciplinas"
      @click="navigateToSubjectsOfUserGoogleId(String(user?.sub))"
      :src="user?.picture"
    />
  </nav>
</template>

<style scoped>
.userProfile {
  display: flex;
  align-items: center;
  gap: 1.25rem;
}

.userProfile nav {
  display: flex;
  gap: 0.75rem;
}

.userProfile h2 {
  font-size: 2rem;
}

.userProfile section {
  display: grid;
  place-items: end;
  gap: .25rem;
}

.userProfile button {
  font-weight: bold;
  color: var(--app-strong-blue);
  background-color: var(--app-blue-soft);
  padding: 0.5rem;
  font-size: 1rem;
  border-radius: 0.75rem;
}

.userProfile button:hover {
  background-color: var(--app-strong-blue);
  color: white;
}

.userProfile img {
  border: .5rem solid white;
  border-radius: 50%;
  width: 4rem;
  height: 4rem;
  cursor: pointer;
}
</style>
<script setup lang="ts">
import { VList } from 'vuetify/components/VList';
import { VPagination } from 'vuetify/components/VPagination';
import SubjectListItem from '@/components/SubjectListItem.vue';
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';
import { computed } from 'vue';
import UserMenu from '@/components/UserMenu.vue';
import type { Subject } from '@/types/Subject';
import type { Ref } from 'vue';
import { SubjectService } from '@/services/SubjectService';

const subjectApi = new SubjectService();

const page = ref(1);
const qntVisiblePages = 11;

const auth = useAuthStore();
const loggedIn = computed(() => auth.loggedIn());
const subjects : Ref<Subject[]> = ref([]);
const qntPages = ref(0);

async function fetchPage() {
  const pageSubject = await subjectApi.getPage(page.value);
  subjects.value = pageSubject.content;
  qntPages.value = pageSubject.totalPages;
}

function getScrollClass() {
  return auth.loggedIn() ? '':'overflow-hidden'
}

onMounted(async () => {
  auth.getCredentialFromLocalStorage();
  fetchPage();
});
</script>

<template>
  <main class="container">
    <header>
      <h1>Listar disciplinas</h1>
      <UserMenu />
    </header>
    <v-list :class="' list ' + getScrollClass()">
      <SubjectListItem
        v-for="subject in subjects"
        :key="subject.code"
        :code="subject.code"
        :department="subject.department"
        :name="subject.name"
      />
      <div v-if="!loggedIn" class="hiddenList"/>
    </v-list>
    <v-pagination
      :length="qntPages"
      v-model="page"
      color="primary"
      @click="fetchPage"
      :total-visible="qntVisiblePages"
      :disabled="!loggedIn"
    ></v-pagination>
  </main>
</template>

<style scoped>

header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.container {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  padding: 2.4rem 10%;
}

h1 {
  font-weight: bold;
  font-size: 4.8rem;
}

.list {
  background: var(--app-blue-soft);
  flex: 1;
  margin: 2.4rem 0;
  border-radius: 0.8rem;
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
  overflow-y: auto;
  position: relative;
}

.list .hiddenList {
  position: absolute;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to top, rgb(0, 0, 0), transparent);
}

.pagination .v-btn {
  border-radius: 0.8rem;
  border: 2px solid var(--app-strong-blue);
}
</style>

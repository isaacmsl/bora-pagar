<script setup lang="ts">
import { VList } from 'vuetify/components/VList';
import { VPagination } from 'vuetify/components/VPagination';
import {
  VExpansionPanel,
  VExpansionPanels,
  VExpansionPanelTitle,
  VExpansionPanelText,
  VTextField,
  VRow,
  VCol,
} from 'vuetify/components';
import SubjectListItem from '@/components/SubjectListItem.vue';
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';
import { computed } from 'vue';
import UserMenu from '@/components/UserMenu.vue';
import CustomSelect from '@/components/CustomSelect.vue';
import type { Subject } from '@/types/Subject';
import type { Ref } from 'vue';
import { SubjectService } from '@/services/SubjectService';
import type { SubjectFilters } from '@/types/SubjectFilters';
import { debounce } from '@/util/debounce';
import { watch } from 'vue';

const subjectApi = new SubjectService();

const page = ref(1);
const qntVisiblePages = 6;

const auth = useAuthStore();
const loggedIn = computed(() => auth.loggedIn());
const subjects: Ref<Subject[]> = ref([]);
const qntPages = ref(0);
const subjectName = ref('');
const subjectDepartment = ref('');
const panel = ref<number[]>([]);

async function fetchPage() {
  const filters : SubjectFilters = {
    name: subjectName.value,
    department: subjectDepartment.value
  };
  const pageSubject = await subjectApi.findAll(filters, page.value - 1);
  subjects.value = pageSubject.content;
  qntPages.value = pageSubject.totalPages;
}

const handleNameInput = debounce(fetchPage);

function getScrollClass() {
  return auth.loggedIn() ? '' : 'overflow-hidden';
}

watch(subjectDepartment, () => {
  fetchPage();
})

onMounted(async () => {
  auth.getCredentialFromLocalStorage();
  fetchPage();
  panel.value = [0];
});
</script>

<template>
  <main class="container">
    <header>
      <h1>Bora Pagar</h1>
      <UserMenu />
    </header>

    <v-expansion-panels class="filterPanel" v-model="panel" v-if="auth.loggedIn()">
      <v-expansion-panel>
        <v-expansion-panel-title class="filterPanelTitle"> Campos de busca </v-expansion-panel-title>
        <v-expansion-panel-text class="filterPanelText">
          <v-row>
            <v-col cols="12" md="6">
              <v-text-field 
                label="Disciplina" 
                variant="outlined" 
                density="comfortable" 
                v-model="subjectName"
                @keyup="handleNameInput"
                placeholder="Nome da disciplina"
                persistent-placeholder
              />
            </v-col>

            <v-col cols="12" md="6">
              <CustomSelect v-model="subjectDepartment"/>
            </v-col>
          </v-row>
        </v-expansion-panel-text>
      </v-expansion-panel>
    </v-expansion-panels>

    <v-list :class="' list ' + getScrollClass()">
      <SubjectListItem
        v-for="subject in subjects"
        :key="subject.code"
        :code="subject.code"
        :department="subject.department"
        :name="subject.name"
        :interested-users="subject.interestedUsers"
      />
      <div v-if="!loggedIn" class="hiddenList" />
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
  flex-wrap: wrap-reverse;
  gap: 4rem;
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
  gap: 2.4rem;
}

h1 {
  font-weight: bold;
  font-size: 4.8rem;
}

.filterPanelTitle {
  font-weight: bold;
  border: 1px solid #363c40;
}

.filterPanelText,
.filterPanelTitle {
  font-size: 1.5rem;
  background: var(--app-blue-soft);
  color: white;
}

.filterPanelText {
  border: 1px solid #363c40;
  padding-top: 2rem;
}

.list {
  background: var(--app-blue-soft);
  flex: 1;
  border-radius: 0.8rem;
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
  overflow-y: auto;
  position: relative;
  border: 1px solid #363c40;
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

@media only screen and (max-width: 960px) {
  .container {
    min-height: 100vh;
    height: auto;
  }

  .list {
    height: 500px;
    flex: auto;
  }

  header {
    justify-content: flex-end;
  }

  h1 {
    width: 100%;
  }
}
</style>

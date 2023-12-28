<script setup lang="ts">
import { VList } from 'vuetify/components/VList';
import { VPagination } from 'vuetify/components/VPagination';
import SubjectListItem from '@/components/SubjectListItem.vue';
import { ref } from 'vue';
const page = ref(1);
const qntSubjectsOnPage = 4;
const qntVisiblePages = 7;

const subjects = [
  {
    code: 'DIM0004',
    name: 'Programação funcional',
    department: 'Departamento de informática e matemática aplicada'
  },
  {
    code: 'IMD0010',
    name: 'Fundamentos matemáticos da computação II',
    department: 'Instituto metrópole digital'
  },
  {
    code: 'IMD0020',
    name: 'Fundamentos matemáticos da computação I',
    department: 'Instituto metrópole digital'
  },
  {
    code: 'IMD00X0',
    name: 'Natação I',
    department: 'Departamento de Educação Física'
  },
  {
    code: 'IMD00X0',
    name: 'Forró I',
    department: 'Departamento de Dança'
  },
  {
    code: 'IMD00X0',
    name: 'Flauta I',
    department: 'Departamento de Música'
  },
  {
    code: 'IMD00X0',
    name: 'Basquete I',
    department: 'Departamento de Educação Física'
  },
  {
    code: 'IMD00X0',
    name: 'Axé I',
    department: 'Departamento de Música'
  },
  {
    code: 'IMD00X0',
    name: 'Introdução a Lógica',
    department: 'Departamento de Informática e Matemática Aplicada'
  }
];

const qntPages = Math.ceil(subjects.length / qntSubjectsOnPage);
</script>

<template>
  <main class="container">
    <h1>Listar disciplinas {{ page }}</h1>
    <v-list class="list">
      <SubjectListItem
        v-for="subject in subjects.slice(qntSubjectsOnPage * (page - 1), qntSubjectsOnPage * page)"
        :key="subject.code"
        :code="subject.code"
        :department="subject.department"
        :name="subject.name"
      />
    </v-list>
    <v-pagination
      :length="qntPages"
      v-model="page"
      color="primary"
      :total-visible="qntVisiblePages"
    ></v-pagination>
  </main>
</template>

<style scoped>
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
  padding: 2.4rem 1.2rem;
  overflow-y: auto;
}

.pagination .v-btn {
  border-radius: 0.8rem;
  border: 2px solid var(--app-strong-blue);
}
</style>

<script setup lang="ts">
import { VIcon } from 'vuetify/components';
import { VList } from 'vuetify/components/VList';
import { VPagination } from 'vuetify/components/VPagination';
import SubjectListItem from '@/components/SubjectListItem.vue';
import { ref } from 'vue';
import { GoogleLogin, decodeCredential, googleLogout } from 'vue3-google-login';
import type { GoogleUserInfo } from '../types/GoogleUserInfo';
import type { Ref } from 'vue';

const page = ref(1);
const qntSubjectsOnPage = 4;
const qntVisiblePages = 7;

const loggedIn = ref(false);
const user : Ref<GoogleUserInfo | undefined> = ref();

function normalizeGivenName(name : String) {
  return name.charAt(0) + name.slice(1).toLowerCase();
}

function googleLoginCallback(response : any) {
  console.log(decodeCredential(response.credential));
  loggedIn.value = true;
  user.value = decodeCredential(response.credential) as GoogleUserInfo;
  user.value.given_name = normalizeGivenName(user.value.given_name);
}

function logout() {
  loggedIn.value = false;
  googleLogout();
}

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
    <header>
      <h1>Listar disciplinas</h1>
      <div>
        <GoogleLogin
          class="googleLogin"
          v-if="!loggedIn"
          :callback="googleLoginCallback"
        />
        <nav class="userProfile" v-if="loggedIn">
          <section>
            <h2>{{ user?.given_name }}</h2>
            <button @click="logout">
              <v-icon icon="mdi-logout"/> Sair
            </button>
          </section>
          <img :src="user?.picture" width="80px"/>
        </nav>
      </div>
    </header>
    <v-list class="list">
      <SubjectListItem
        v-for="subject in subjects.slice(qntSubjectsOnPage * (page - 1), qntSubjectsOnPage * page)"
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

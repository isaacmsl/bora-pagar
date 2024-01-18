<script setup lang="ts">
import { VList, VPagination } from 'vuetify/components';
import SubjectListItem from '@/components/SubjectListItem.vue';
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';
import UserMenu from '@/components/UserMenu.vue';
import type { Subject } from '@/types/Subject';
import type { Ref } from 'vue';
import { useRoute, onBeforeRouteUpdate } from 'vue-router';
import { UserService } from '@/services/UserService';
import type { AppUser } from '@/types/AppUser';
import { SubjectService } from '@/services/SubjectService';
const userService = new UserService();
const subjectService = new SubjectService();

const route = useRoute();
const auth = useAuthStore();
const subjects: Ref<Subject[]> = ref([]);
const user: Ref<AppUser | undefined> = ref();
const page = ref(1);
const qntPages = ref(0);
const qntVisiblePages = 6;

async function updateViewData(googleId : string) {
    auth.getCredentialFromLocalStorage();
    user.value = await userService.searchUserByGoogleId(googleId);
    fetchUserSubjects(googleId);
}

async function fetchUserSubjects(googleId : string) {
    const pageSubject = await subjectService.findAllByGoogleId(googleId, page.value - 1);
    subjects.value = pageSubject.content;
    qntPages.value = pageSubject.totalPages;
}

onMounted(() => {
    updateViewData(String(route.params.googleId));
});

onBeforeRouteUpdate((to) => {
    updateViewData(String(to.params.googleId));
});
</script>

<template>
    <main class="container">
        <header>
            <section class="userInfo">
                <img class="userPicture" :src="user?.pictureUri" alt="Imagem do usuário buscado" />
                <div>
                    <h2>Bora Pagar</h2>
                    <h1>{{ user?.name }}</h1>
                </div>
            </section>
            <UserMenu />
        </header>

        <v-list class="list">
            <SubjectListItem v-for="subject in subjects" :key="subject.code" :component-i-d="subject.componentID" :code="subject.code"
                :department="subject.department" :name="subject.name" :interested-users="subject.interestedUsers" />
        </v-list>

        <v-pagination :length="qntPages" v-model="page" color="primary" @click="fetchUserSubjects" :total-visible="qntVisiblePages"
            :disabled="!auth.loggedIn()"></v-pagination>
    </main>
</template>

<style scoped>
header {
    display: flex;
    flex-wrap: wrap-reverse;
    gap: 2.5rem;
    align-items: center;
    justify-content: space-between;
}

.container {
    width: 100vw;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    padding: 1.5rem 10%;
    gap: 1.5rem;
}

h1 {
    font-weight: bold;
    font-size: 3rem;
}

.list {
    background: var(--app-blue-soft);
    flex: 1;
    border-radius: 0.5rem;
    display: flex;
    flex-direction: column;
    gap: 0.75rem;
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

.userPicture {
    border: .5rem solid white;
    border-radius: 50%;
}

.userInfo {
    display: flex;
    align-items: center;
    gap: 1.25rem;
}

@media only screen and (max-width: 960px) {
  h1 {
    font-size: 2rem;
  }
}

@media only screen and (max-width: 1280px) {
    header {
        justify-content: flex-end;
    }

    .userInfo {
        width: 100%;
    }
}
</style>

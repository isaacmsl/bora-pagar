<script setup lang="ts">
import { VListItem } from 'vuetify/components/VList';
import Button from '@/components/Button.vue';
import type { AppUser } from '@/types/AppUser';
import { useAuthStore } from '@/stores/auth';
import { SubjectService } from '@/services/SubjectService';
import { ref } from 'vue';
import { onMounted } from 'vue';
import type { Subject } from '@/types/Subject';
import { navigateToSubjectsOfUserGoogleId } from '@/util/navigation';

const props = defineProps<{
  code: string;
  name: string;
  department: string;
  interestedUsers: AppUser[]
}>();

const subjectService = new SubjectService();
const auth = useAuthStore();
const isUserInterested = ref(false);
const isHandlingInterestedUser = ref(false);

async function handleInterestedUser(isAdd : boolean) {
  isHandlingInterestedUser.value = true;
  const credential = auth.getCredentialFromLocalStorage();
  let subject : Subject;

  if (isAdd) {
    subject = await subjectService.addInterestedUserByCode(credential, props.code);
  } else {
    subject = await subjectService.removeInterestedUserByCode(credential, props.code);
  }

  isUserInterested.value = subjectContainsInterestedUser(subject.interestedUsers);
  isHandlingInterestedUser.value = false;
}

function subjectContainsInterestedUser(interestedUsers : AppUser[]) {
  return interestedUsers.find(user => user.googleId == auth.user?.sub) != undefined;
}

onMounted(() => {
  isUserInterested.value = subjectContainsInterestedUser(props.interestedUsers);
});
</script>

<template>
  <v-list-item>
    <div class="list-item">
      <div class="subject-info">
        <h2 class="subject-name">{{ code }} - {{ name }}</h2>

        <div class="subject-extra">
          <p class="subject-departament">{{ department }}</p>
        </div>
      </div>

      <div class="subject-actions">
        <Button
          @click="handleInterestedUser(true)"
          v-if="!isUserInterested && auth.loggedIn()"
          :disabled="isHandlingInterestedUser"
          name="Pagarei"
        />
        <Button
          @click="handleInterestedUser(false)"
          v-if="isUserInterested && auth.loggedIn()" 
          :disabled="isHandlingInterestedUser"
          name="Não pagarei"
        />

        <div v-if="0 < interestedUsers.length && interestedUsers.length <= 3" class="interested-users">
          <img
            @click="navigateToSubjectsOfUserGoogleId(user.googleId)"
            v-for="user in props.interestedUsers" 
            class="interested-user-picture"
            :key="user.username" 
            :src="user.pictureUri" 
            :alt="`Foto de perfil de ${user.name}`"
            :title="user.name"
          >
          <span>{{ interestedUsers.length == 1 ? "Vai" : "Vão" }} pagar</span>
        </div>
        <div v-else-if="interestedUsers.length > 3" class="interested-users">
          <img 
            v-for="n in 3" 
            class="interested-user-picture"
            :key="props.interestedUsers[n-1].username" 
            :src="props.interestedUsers[n-1].pictureUri" 
            :alt="`Foto de perfil de ${props.interestedUsers[n-1].name}`"
            :title="props.interestedUsers[n-1].name"
          >
          <span>+ {{ interestedUsers.length - 3 }} pessoas</span>
        </div>
      </div>

      
    </div>
  </v-list-item>
</template>

<style scoped>
.list-item {
  height: 110px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--app-dark-blue);
  border-radius: 1.6rem;
  padding: 2rem 3.4rem;
  color: var(--default-color);
  gap: 2.4rem;
}

.subject-info {
  flex: 1;
}

.subject-name {
  font-weight: bold;
  font-size: 2.4rem;
}

.subject-extra {
  margin-top: 0.8rem;
}

.subject-departament {
  font-size: 1.4rem;
}

.interested-user-picture {
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  border: 2px solid white;
  cursor: pointer;
}

.interested-users {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  align-items: center;
  gap: 0.5rem;
}

.interested-users span {
  color: var(--app-strong-blue);
}

.subject-actions {
  text-align: center;
}
</style>

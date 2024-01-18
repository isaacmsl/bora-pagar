<script setup lang="ts">
import { VListItem } from 'vuetify/components/VList';
import Button from '@/components/Button.vue';
import type { AppUser } from '@/types/AppUser';
import { useAuthStore } from '@/stores/auth';
import { SubjectService } from '@/services/SubjectService';
import { ref } from 'vue';
import { onMounted } from 'vue';
import type { Subject } from '@/types/Subject';
import UserSmallAvatar from './UserSmallAvatar.vue';

const props = defineProps<{
  code: string;
  name: string;
  department: string;
  interestedUsers: AppUser[];
  componentID: string
}>();

const subjectService = new SubjectService();
const auth = useAuthStore();
const isUserInterested = ref(false);
const isHandlingInterestedUser = ref(false);

async function handleInterestedUser(isAdd: boolean) {
  isHandlingInterestedUser.value = true;
  const credential = auth.getCredentialFromLocalStorage();
  let subject: Subject;

  if (isAdd) {
    subject = await subjectService.addInterestedUserByComponentID(credential, props.componentID);
  } else {
    subject = await subjectService.removeInterestedUserByComponentID(credential, props.componentID);
  }

  isUserInterested.value = subjectContainsInterestedUser(subject.interestedUsers);
  isHandlingInterestedUser.value = false;
}

function subjectContainsInterestedUser(interestedUsers: AppUser[]) {
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
        <h2 class="subject-name text-truncate">{{ code }} - {{ name }}</h2>

        <div class="subject-extra">
          <p class="subject-departament">{{ department }}</p>
        </div>
      </div>

      <div class="subject-actions">
        <!-- <div class="isUserInterested-buttons"> -->
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
            color="danger"
          />
        <!-- </div> -->
        <div v-if="0 < interestedUsers.length && interestedUsers.length <= 3" class="interested-users">
          <UserSmallAvatar 
            v-for="user in interestedUsers"
            :key="user.username"
            :user="user"
          />
          <span>{{ interestedUsers.length == 1 ? "Vai" : "Vão" }} pagar</span>
        </div>
        <div v-else-if="interestedUsers.length > 3" class="interested-users">
          <UserSmallAvatar 
            v-for="index in 3"
            :key="interestedUsers[index - 1].username"
            :user="interestedUsers[index - 1]"
          />
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
  border-radius: 1rem;
  padding: 1.25rem 2rem;
  color: var(--default-color);
  gap: 1.5rem;
}

.subject-info {
  max-width: 70%;
  display: block;
}

.subject-name {
  font-weight: bold;
  font-size: 1.5rem;
}

.subject-extra {
  margin-top: 0.5rem;
}

.subject-departament {
  font-size: 1rem;
}



.interested-users {
  display: flex;
  justify-content: center;
  margin-top: 0.75rem;
  align-items: center;
  gap: 0.5rem;
}

.interested-users span {
  color: var(--app-strong-blue);
}

.subject-actions {
  display: flex;
  flex-direction: column;
  justify-content: end;
}

@media only screen and (max-width: 960px) {
  .subject-name {
    font-size: 1.25rem;
  }

  .list-item {
    display: grid;
    grid-template-columns: 1fr;
    height: auto;
    justify-content: start;
    box-sizing: content-box;
  }

  .subject-info {
    width: 100%;
    max-width: unset;
    overflow: hidden;
  }

  .subject-actions {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
  }

  .subject-actions button {
    margin-left : 0;
    margin-right: 0.75rem;
  }

  .interested-users {
    margin-top: 0;
  }
}
</style>

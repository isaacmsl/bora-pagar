<script setup lang="ts">
import type { AppUser } from '@/types/AppUser';
import { UserService } from '@/services/UserService';
import { navigateToSubjectsOfUserGoogleId } from '@/util/navigation';
import { ref } from 'vue';
import Button from '@/components/Button.vue';
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';

const props = defineProps<{
  user: AppUser
}>()

const auth = useAuthStore();
let credential = auth.getCredentialFromLocalStorage();
const userService = new UserService();
const hasCheckedFriendship = ref(false);
const isFriend = ref(false);
const isHandlingInterestedFriend = ref(false);

async function handleInterestedFriend(googleId : string, wantsToBeFriend : boolean) {
  isFriend.value = wantsToBeFriend;
  credential = auth.getCredentialFromLocalStorage();
  let user: AppUser;

  if(wantsToBeFriend) {
    user = await userService.addFriend(credential, googleId);
  } else {
    user = await userService.removeFriend(credential, googleId);
  }

  const userFriends = await userService.findFriends(credential);
  isHandlingInterestedFriend.value = containsFriend(userFriends);
}

function containsFriend(friends : AppUser[]) : boolean {
  return friends.find(friend => friend.googleId == props.user.googleId) != undefined
}

async function checkFriendship() {
  const userFriends = await userService.findFriends(credential);

  if(userFriends != undefined) {
    hasCheckedFriendship.value = true;
  }

  isFriend.value = containsFriend(userFriends);
}

onMounted(() => {
  checkFriendship();
});

</script>

<template>
  <li  class="userItemContainer">
    <div class="clickableContainer" 
    @click="navigateToSubjectsOfUserGoogleId(user.googleId)"
    :title="`Ver disciplinas de ${user.name}`">
      <img :src="user.pictureUri" :alt="`Foto de perfil do usuário ${user.name}`">
      <div class="userItemInfo">
        <h2>{{ user.name }}</h2>
        <p>@{{ user.username }}</p>
      </div>
    </div>
    <div class="followButton" v-if="hasCheckedFriendship">
      <Button 
        @click="handleInterestedFriend(user.googleId, true)"
        v-if="!isFriend && auth.loggedIn() && user.googleId != auth.user?.sub"
        :disabled="isHandlingInterestedFriend"
        name="Seguir"
      />
      <Button 
        @click="handleInterestedFriend(user.googleId, false)"
        v-if="isFriend && auth.loggedIn() && user.googleId != auth.user?.sub"
        :disabled="isHandlingInterestedFriend"
        name="Não seguir"
        color="danger"
      />
    </div>
  </li>
</template>

<style scoped>
.userItemContainer {
  cursor: pointer;
  width: 100%;
  height: 100%;
  background-color: #1D2030;
  border: 1px solid #363C40;
  padding: 1rem;
  display: flex;
  align-items: center;
  border-radius: 0.5rem;
}

.userItemContainer:hover {
  background-color: #171926;
}

.clickableContainer {
  display: flex;
  align-items: center;
  width: 100%;
  height: 100%;
}

.followButton {
  display: flex;
  align-items: end;
  text-wrap: nowrap;
}

.userItemContainer img {
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
}

.userItemInfo {
  margin-left: 1.25rem;
}

.userItemInfo p {
  font-size: 1rem;
  color: #938888;
}

.userItemInfo h2 {
  font-size: 1.5rem;
  font-weight: bold;
}

@media only screen and (max-width: 600px) {
  .userItemInfo h2 {
    font-size: 1.25rem;
  }
}
</style>
<script setup lang="ts">
import type { AppUser } from '@/types/AppUser';
import { navigateToSubjectsOfUserGoogleId } from '@/util/navigation';
// import Button from '@/components/Button.vue';

defineProps<{
  user: AppUser
}>()
</script>

<template>
  <li :title="`Ver disciplinas de ${user.name}`" class="userItemContainer"
    @click="navigateToSubjectsOfUserGoogleId(user.googleId)">
    <img :src="user.pictureUri" :alt="`Foto de perfil do usuário ${user.name}`">
    <div class="userItemInfo">
      <h2>{{ user.name }}</h2>
      <p>@{{ user.username }}</p>
    </div>
    <!-- <Button name="Conectar"/> -->
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
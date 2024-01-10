<script setup lang="ts">
import type { AppUser } from '@/types/AppUser';
import { navigateToSubjectsOfUser } from '@/util/navigation';
// import Button from '@/components/Button.vue';

defineProps<{
  user: AppUser
}>()
</script>

<template>
  <li
    :title="`Ver disciplinas de ${user.name}`"
    class="userItemContainer"
    @click="navigateToSubjectsOfUser(user)">
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
  padding: 1.5rem;
  display: flex;
  align-items: center;
  border-radius: 0.5rem;
}

.userItemContainer:hover {
  background-color: #171926;
}

.userItemContainer img {
  width: 6rem;
  height: 6rem;
  border-radius: 50%;
}

.userItemInfo {
  margin-left: 2rem;
}

.userItemInfo p {
  font-size: 1.5rem;
  color: #938888;
}

.userItemInfo h2 {
  font-size: 2.5rem;
  font-weight: bold;
}

</style>
<script setup lang="ts">
import type { AppUser } from '@/types/AppUser';
import { navigateToSubjectsOfUserGoogleId } from '@/util/navigation';
const props = defineProps<{user: AppUser}>();
</script>

<template>
  <img
    @click="navigateToSubjectsOfUserGoogleId(props.user.googleId)"
    class="interested-user-picture"
    :src="props.user.pictureUri" 
    :alt="`Foto de perfil de ${props.user.name}`"
    :title="props.user.name"
  />
</template>

<style scoped>
.interested-user-picture {
  width: 2rem;
  height: 2rem;
  border-radius: 50%;
  border: 2px solid white;
  cursor: pointer;
}
</style>
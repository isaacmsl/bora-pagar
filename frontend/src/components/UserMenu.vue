<script setup lang="ts">
import UserProfile from '@/components/UserProfile.vue';
import { useAuthStore } from '@/stores/auth';
import { computed } from 'vue';
import { onMounted } from 'vue';

const auth = useAuthStore();
const loggedIn = computed(() => auth.loggedIn());

onMounted(() => {
  auth.getCredentialFromLocalStorage();
});

</script>

<template>
  <div>
    <GoogleLogin
      class="googleLogin"
      v-if="!loggedIn"
      :callback="auth.googleLoginCallback"
    />
    <UserProfile v-if="loggedIn"/>
  </div>
</template>

<style scoped>

</style>
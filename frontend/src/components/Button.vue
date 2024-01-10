<script setup lang="ts">
defineProps<{
  name: string;
}>();
</script>

<template>
  <button>{{ name }}</button>
</template>

<style scoped>

button {
  margin-left: auto;
  border: 2px solid #2591D7;
  border-radius: 0.5rem;
  font-size: 1rem;
  font-weight: bold;
  padding: 0.5rem 0.75rem;
  color: #2591D7;
  transition: all 200ms;
}

button:hover {
  background-color: #2591D7;
  color: white;
}
</style>

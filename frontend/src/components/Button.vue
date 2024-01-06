<script setup lang="ts">

function foo(){
    alert("bar");
}

defineProps<{
  name: string;
}>();
</script>

<template>
  <button @click="foo">{{ name }}</button>
</template>

<style scoped>

button {
  margin-left: auto;
  border: 2px solid #2591D7;
  border-radius: 0.5rem;
  font-size: 1.8rem;
  font-weight: bold;
  padding: 0.5rem 1rem;
  color: #2591D7;
  transition: all 200ms;
}

button:hover {
  background-color: #2591D7;
  color: white;
}
</style>

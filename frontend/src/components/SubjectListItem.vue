<script setup lang="ts">
import { VListItem } from 'vuetify/components/VList';
import Button from '@/components/Button.vue';
defineProps<{
  code: string;
  name: string;
  department: string;
}>();
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

      <Button name="Quero Pagar"/>
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
</style>

<script setup lang="ts">
import {VListItem} from "vuetify/components/VList";
defineProps<{
  code: string,
  name: string,
  department: string
}>()
</script>

<template>
  <v-list-item>
    <div class="list-item">
      <div class="subject-info">
        <h2 class="subject-name">
          {{ code }} - {{ name }}
        </h2>

        <div class="subject-extra">
          <p class="subject-departament">{{ department }}</p>
        </div>
      </div>

      <button class="cta-button">
        Quero Pagar
      </button>
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
  padding: 2.0rem 3.4rem;
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

.cta-button {
  background-color: var(--app-strong-blue);
  color: var(--app-white);
  border-radius: 0.8rem;
  font-weight: bold;
  font-family: "Inter", sans-serif;
  font-size: 1.6rem;
  border: none;
  padding: 1.2rem 2.4rem;
  cursor: pointer;
  transition: background-color ease 200ms;
}

.cta-button:hover{
  background-color: var(--app-strong-blue-hover);
}
</style>
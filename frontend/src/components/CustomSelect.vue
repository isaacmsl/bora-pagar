<script setup lang="ts">
import { VAutocomplete, VListItem } from 'vuetify/components';

const departments = ["Instituto metrópole digital", "Departamento de Informática e Matemática Aplicada"]
</script>

<template>
  <v-autocomplete 
    :items="departments" 
    label="Departamento" 
    variant="outlined" 
    density="comfortable" 
    bg-color="#282c42"
    no-data-text="Não há departamentos correspondentes"
  >
    <template v-slot:item="{props}">
      <v-list-item v-bind="props" class="selectItem"/>
    </template>
  </v-autocomplete>
</template>

<style scoped>
.selectItem {
  color: white;
  font-size: 1.5rem;
  background-color: var(--app-blue-soft)
}
</style>
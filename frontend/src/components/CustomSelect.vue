<script setup lang="ts">
import { VAutocomplete, VListItem } from 'vuetify/components';

const model = defineModel<string>();
const departments = ["INSTITUTO METRÓPOLE DIGITAL", "DEPARTAMENTO DE INFORMÁTICA E MATEMÁTICA APLICADA"];
</script>

<template>
  <v-autocomplete 
    :items="departments" 
    v-model="model"
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
  background-color: var(--app-blue-soft)
}
</style>
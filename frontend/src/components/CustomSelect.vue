<script setup lang="ts">
import { VAutocomplete, VListItem } from 'vuetify/components';

const props = defineProps<{modelValue: string}>();
const emit = defineEmits(['update:modelValue']);

function handleSelection(value: string) {
  emit('update:modelValue', value);
}

const departments = ["INSTITUTO METRÓPOLE DIGITAL", "DEPARTAMENTO DE INFORMÁTICA E MATEMÁTICA APLICADA"];
</script>

<template>
  <v-autocomplete 
    :items="departments" 
    :model-value="props.modelValue"
    @update:model-value="handleSelection"
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
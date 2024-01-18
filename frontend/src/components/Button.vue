<script setup lang="ts">
const props = defineProps({
  name : {
    type: String,
    required: true,
  },
  color: {
    type: String,
    default: "primary",
    validator(value : String) {
      return ['primary', 'danger'].includes(value.toString());
    }
  }
});

const classes : {[key : string] : string}= {
  primary : "buttonPrimary",
  danger : "buttonDanger",
}
</script>

<template>
    <button :class="classes[props.color]">{{ name }}</button>
</template>

<style scoped>

button {
  margin-left: auto;
  border-radius: 0.5rem;
  font-size: 1rem;
  font-weight: bold;
  padding: 0.5rem 1rem;
  transition: all 200ms;
}

.buttonPrimary {
  color: #2591D7;
  border: 2px solid #2591D7;
}

.buttonPrimary:hover {
  background-color: #2591D7;
  color: white;
}

.buttonDanger {
  border: 2px solid #e82323;
  color: #e82323;
}

.buttonDanger:hover {
  background-color: #e82323;
  color: white;
}
</style>

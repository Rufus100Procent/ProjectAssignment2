<template>
  <Collection msg="Welcome to Your Vue.js App" />
</template>

<script>
import Collection from './collection.vue'

export default {
  name: 'App',
  components: {
    Collection
  }
}
</script>

<style>
.object-fit {
  object-fit: cover;
  object-position: center;
}
.h-520 {
  height: 520px;
}
</style>

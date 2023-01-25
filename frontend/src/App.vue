<template>
  <router-view
    v-slot="{ Component }"
    name="header"
  >
    <transition
      name="fade"
      mode="out-in"
    >
      <div :key="$route.name">
        <component
          :is="Component"
        />
      </div>
    </transition>
  </router-view>
  <router-view v-slot="{ Component }">
    <transition
      name="fade"
      mode="out-in"
    >
      <div :key="$route.name">
        <component
          :is="Component"
        />
      </div>
    </transition>
  </router-view>
  <router-view name="footer">
    <salt-footer />
  </router-view>
</template>
<script>
import SaltHeader from '@/components/SaltHeader.vue'
import SaltFooter from '@/components/SaltFooter.vue'

export default {
  name: 'App',
  components: {
    SaltHeader,
    SaltFooter,
  }
}
</script>

<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.title-1 {
  font-size: 3.5rem;
}

.fs-10 {
  font-size: .85rem;
}

.fs-12 {
  font-size: .7rem;
}
.fs-11 {
  font-size: .775rem;
}

button {
  border: 1px solid lightgrey;
  border-radius: 2px;
}
button:hover {
  border: 1px solid rgb(237, 236, 236);
}
button:active {
  border: 1px solid rgb(182, 181, 181);
}
</style>

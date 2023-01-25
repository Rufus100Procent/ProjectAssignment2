<template>
  <header class="w-100 mb-4 border-secondary border-bottom">
    <div class="container-fluid bg-secondary py-3 text-center">
      <p class="text-white mb-0 text-uppercase">1 - 3 days of delivery | 30 days refundable</p>
    </div>
    <div class="container">
      <div class="row justify-content-center py-4">
        <div class="col-4 d-none d-md-flex px-4 text-left align-items-center" />
        <router-link
          to="/"
          class="col-4 justify-content-center"
        >
          <img
            src="../assets/images/salt.svg"
            class="header-logo"
          >
        </router-link>
        <div class="col-4 justify-content-end text-end px-4 align-items-center d-flex">
          <cart-icon />
        </div>
      </div>
    </div>
    <div class="w-100 border-secondary border-bottom" />
    <div class="container border-left">
      <nav class="row justify-content-center">
        <router-link
          v-slot="{ isActive, navigate }"
          class=""
          :to="{
            name: 'categories',
            params: {
              category: 'hats'
            }
          }"
          custom
        >
          <p
            class="mb-0 navitem col-3 border-secondary border-right p-4 text-center pointer text-decoration-none text-dark"
            :class="isActive ? 'fw-bold' : ''"
            @click="navigate"
          >
            Hats
          </p>
        </router-link>
        <router-link
          v-slot="{ isActive, navigate }"
          class=""
          to="/categories/jackets"
          custom
        >
          <p
            class="mb-0 navitem col-3 border-secondary border-right p-4 text-center pointer text-decoration-none text-dark"
            :class="isActive ? 'fw-bold' : ''"
            @click="navigate"
          >
            Jackets
          </p>
        </router-link>
        <router-link
          v-slot="{ isActive, navigate }"
          class=""
          to="/categories/tshirts"
          custom
        >
          <p
            class="mb-0 navitem col-3 border-secondary border-right p-4 text-center pointer text-decoration-none text-dark"
            :class="isActive ? 'fw-bold' : ''"
            @click="navigate"
          >
            T-shirts
          </p>
        </router-link>
        <router-link
          v-slot="{ isActive, navigate }"
          class=""
          to="/categories/bags"
          custom
        >
          <p
            class="mb-0 navitem col-3 border-secondary border-right p-4 text-center pointer text-decoration-none text-dark"
            :class="isActive ? 'fw-bold' : ''"
            @click="navigate"
          >
            Bags
          </p>
        </router-link>
      </nav>
    </div>
  </header>
</template>
<script>
import CartIcon from '@/components/Cart/CartIcon.vue'

export default {
  components: {
    CartIcon
  }
}
</script>

<style>
.border-right { border-right: 1px solid black; }
.pointer { cursor: pointer; }
.header-logo { width: 175px; }
.border-left { border-left: rgb(108, 117, 125) solid 0.998264px !important; }
nav .navitem:hover { background: black; color: white !important; font-weight: 900; }
.text-left { text-align: left !important; }
</style>
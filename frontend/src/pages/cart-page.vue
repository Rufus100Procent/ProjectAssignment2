<template>
  <div class="container px-3">
    <cart-list
      class="py-5"
      size="xl"
    />
    <button
      v-if="hasItems"
      data-testid="reset-cart-button"
      style="margin-right: 20px"
      @click="resetCart"> Reset cart </button>
    <button
      v-if="hasItems"
      data-testid="buy-button"
      @click="buy"> Cash me out </button>
  </div>
</template>
<script>
import CartList from '@/components/Cart/CartList'

export default {
  components: {
    CartList
  },
  computed: {
    hasItems() {
      return !!this.$store.state.cart.length
    }
  },
  methods: {
    buy () {
      this.$store.dispatch('buyCart', 1)
    },
    resetCart () {
      this.$store.dispatch('resetCart', 1)
    }
  }
}
</script>
<style>
</style>
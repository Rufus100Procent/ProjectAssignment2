<template>
  <div
    data-testid="expanded-class"
    class="d-inline-block position-relative cart-icon"
    :class=" expanded ? 'expanded' : ''"
  >
    <router-link to="/cart">
      <svg
        style="width: 18px;"
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 448 512"
      >
        <!--! Font Awesome Pro 6.1.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
        <path
          d="M112 112C112 50.14 162.1 0 224 0C285.9 0 336 50.14 336 112V160H400C426.5 160 448 181.5 448 208V416C448 469 405 512 352 512H96C42.98 512 0 469 0 416V208C0 181.5 21.49 160 48 160H112V112zM160 160H288V112C288 76.65 259.3 48 224 48C188.7 48 160 76.65 160 112V160zM136 256C149.3 256 160 245.3 160 232C160 218.7 149.3 208 136 208C122.7 208 112 218.7 112 232C112 245.3 122.7 256 136 256zM312 208C298.7 208 288 218.7 288 232C288 245.3 298.7 256 312 256C325.3 256 336 245.3 336 232C336 218.7 325.3 208 312 208z"
        />
      </svg>
    </router-link>
    <div
      v-if="$store.state.cart.length"
      class="position-absolute cart-icon-number d-flex align-items-center justify-content-center text-center"
    >
      {{ amount_of_products }}
    </div>
    <div class="position-absolute cart-icon-list bg-white">
      <br>
      <cart-list />
      <br>
    </div>
  </div>
</template>
<script>
import CartList from './CartList'

export default {
  components: {
    CartList,
  },
  data () {
    return {
      val: '',
      expanded: false,
      expandTimeout: null
    }
  },
  computed: {
    amount_of_products () {
      const cart = this.$store.state.cart
      let amount = 0;
      for (let i = 0; i < cart.length; i++ )
        amount += cart[i].quantity
      return amount
    }
  },
  watch: {
    amount_of_products () {
      this.expanded = true
      clearTimeout(this.expandTimeout)
      this.expandTimeout = setTimeout(() => this.expanded = false , 4000)
    }
  },
}

</script>
<style>
.cart-icon {
  z-index: 100;
}
.cart-icon-list {
  box-sizing: border-box;
  height: 0;
  width: 500px;
  transition: height .3s ease-in;
  overflow: scroll;
  top: -10px;
  left: -460px;
  z-index: -1;
  padding: 0 2rem;
}
.cart-icon.expanded .cart-icon-list,
.cart-icon:hover .cart-icon-list {
  height: 200px;
  border: 1px solid lightgrey;
  border-radius: 4px;
}
.cart-icon-number {
  border-radius: 50%;
  width: 24px;
  height: 24px;
  font-size: .75rem;
  /* color: white; */
  background: rgb(240, 240, 240);
  left: 15px;
  top: 15px;
}
</style>

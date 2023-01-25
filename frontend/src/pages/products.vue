<!-- eslint-disable vue/no-use-v-if-with-v-for -->
<template>
  <div
    v-if="isLoading"
    class="py-5"
  >
    <h1 data-testid="loading">
      Loading...
    </h1>
  </div>
  <div
    v-else-if="noVariants"
    class="container"
  >
    <p>No variants</p><div class="row text-left">
      <div class="col-12">
        <p class="text-uppercase fs-12 fw-semibold">
          <router-link
            class="text-decoration-none text-dark"
            to="/"
          >
            SALT MERCH
          </router-link> /
          <router-link
            class="text-decoration-none text-dark"
            :to="'/categories/' + product.category"
          >
            {{ product.category }}
          </router-link> /
          <router-link
            class="text-decoration-none text-dark"
            :to="'/products/' + product.id"
          >
            {{ product.title }}
          </router-link>
        </p>
      </div>
      <div class="col-4">
        <img
          v-if="product.previewImage"
          :src="tryToRequireAsset(product.previewImage)"
          class="selected-product-img"
        >
      </div>
      <div class="col-8">
        <h1 data-testid="title">
          {{ product.title }}
        </h1>
        <p> No color variants for this product! </p>
        <hr class="my-3">
        <div data-testid="description" v-html="product.description" />
      </div>
    </div>
  </div>
  <div
    v-else-if="error"
    class="py-5"
  >
    <h1 data-testid="error">
      error: {{ error }}
    </h1>
  </div>
  <div
    v-else
    class="container"
  >
    <div class="row text-left">
      <div class="col-12">
        <p class="text-uppercase fs-12 fw-semibold">
          <router-link
            class="text-decoration-none text-dark"
            to="/"
          >
            SALT MERCH
          </router-link> /
          <router-link
            class="text-decoration-none text-dark"
            :to="'/categories/' + product.category"
          >
            {{ product.category }}
          </router-link> /
          <router-link
            class="text-decoration-none text-dark"
            :to="'/products/' + product.id"
          >
            {{ product.title }}
          </router-link>
        </p>
      </div>
      <div class="col-4">
          <img
            v-for="image in [currentImage]"
            :key="image"
            :src="image"
            class="selected-product-img"
          >
        <br>
        <br>
        <product-selectables
          v-model:activeIndex="imgIndex"
          :selectables="currentImages"
          selectables-type="images"
        />
      </div>
      <div class="col-8">
        <h1 data-testid="title">
          {{ product.title }}
        </h1>
        <p>
          {{ currentColor.colorName }} /
          <span v-if="sizeIndex !== null && currentSize !== null && currentSize.stock"> Stock: {{ currentSize.stock }} </span>
          <span v-else-if="sizeIndex !== null"> Out of stock </span>
          <span v-else> No size chosen </span>
        </p>
        <hr class="my-3">
        <product-selectables
          v-model:activeIndex="colorIndex"
          :selectables="product.colorVariants"
          selectables-type="colors"
        />
        <br>
        <br>
        <product-selectables
          v-model:activeIndex="sizeIndex"
          :selectables="currentColor.sizes"
          selectables-type="sizes"
        />
        <br>
        <br>
        <button
          class="px-5 py-3"
          :disabled="sizeIndex == null"
          data-testid="buybtn"
          @click="addItem"
        >
          Add {{ product.title }} to cart
        </button>
        <hr class="my-3">
        <div data-testid="description" v-html="product.description" />
      </div>
    </div>
  </div>
</template>

<script>
// import client, { getCart, setCart, resetCart, setProductStock } from '@/services/api-client'
import client, { setCart } from '@/services/api-client'
import ProductSelectables from '@/components/ProductSelectables.vue'
import { tryToRequireAsset } from '@/utilities.js'

export default {
  name: 'App',
  components: {
    ProductSelectables
  },
  async beforeRouteUpdate(to, _, next) {
    this.products = null
    this.product = await client.getProductById(to.params.id)
    next()
  },
  data () {
    return {
      product: null,
      colorIndex: 0,
      imgIndex: 0,
      sizeIndex: null,
      isLoading: true,
      noVariants: false,
      error: null,
    }
  },
  computed: {
    currentColor () {
      return this.product.colorVariants[this.colorIndex]
    },
    currentImages () {
      return this.currentColor?.images || []
    },
    currentImage () {
      // return this.currentImages.length ? require(`@/assets/${this.currentImages[this.imgIndex]}`) : require(`@/assets/${this.product.previewImage}`)
      try {
        return require(`@/assets/${this.currentImages[this.imgIndex]}`)
      } catch (e) {
        return tryToRequireAsset(this.product.previewImage)
      }
    },
    currentSize () {
      return this.currentColor.sizes[this.sizeIndex]
    }
  },
  watch: {
    colorIndex () {
      this.imgIndex = 0
      this.sizeIndex = null
    }
  },
  async mounted () {
    try {
      this.product = await client.getProductById(this.$route.params.id)
      console.log("this.product=", this.product);
      if (!this.product.colorVariants?.length)
        this.noVariants = true
      if (!this.product.previewImage) this.product.previewImage = 'images/logo.png'
    } catch (e) {
      this.error = e.message
    } finally {
      this.isLoading = false
    }
  },
  methods: {
    async addItem () {
      console.log("products.vue addItem() : calling api-client.js:setCart() ..........");
      if (this.product.colorVariants[this.colorIndex].sizes[this.sizeIndex].stock < 1) return
      let cartItem = {
        ...this.product,
        productId: this.product.id,
        color: this.currentColor.colorName,
        size: this.currentSize.size
      }
      const succ = await this.$store.dispatch('addItem', cartItem)
      if (succ)
        this.product.colorVariants[this.colorIndex].sizes[this.sizeIndex].stock--
      else alert('Error adding item to cart')
    },
    tryToRequireAsset,
  }
}
</script>

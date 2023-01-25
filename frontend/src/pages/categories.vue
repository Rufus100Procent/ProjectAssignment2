<template>
  <div
    v-if="category"
    class="container"
  >
    <div class="row">
      <div class="col-12 text-left">
        <p class="text-uppercase fs-12 fw-semibold">
          <router-link
            class="text-decoration-none text-dark"
            to="/"
          >
            SALT MERCH
          </router-link> /
          <router-link
            class="text-decoration-none text-dark"
            :to="'/categories/' + category.category"
          >
            {{ category.category }}
          </router-link>
        </p>
      </div>
      <div class="col-12 px-5">
        <h2 data-testid="title">
          {{ category.title }}
        </h2>
        <p data-testid="description"> {{ category.description }} </p>
      </div>
    </div>
    <collection-view :collection="category.products" />
  </div>
  <div
    v-else-if="isLoading"
    class="container"
  >
    <h1 data-testid="loading">
      Loading...
    </h1>
    <p>
      Category: {{ $route.params.category }}
    </p>
  </div>
  <div
    v-else
    class="container"
  >
    <h1 data-testid="error">
      error...
    </h1>
  </div>
</template>

<script>
import CollectionView from '@/components/CollectionView.vue'
import client from '@/services/api-client'

export default {
  name: 'App',
  components: {
    CollectionView
  },
  async beforeRouteUpdate(to, _, next) {
    this.category = null
    try {
      this.category = await client.getProductsByCategory(to.params.category)
    } catch (e) {
      this.error = true
    } finally {
      this.isLoading = false
    }
    next()
  },
  data () {
    return {
      category: null,
      isLoading: true,
    }
  },
  async mounted () {
      try {
        this.category = await client.getProductsByCategory(this.$route.params.category)
      } catch (e) {
        this.error = true
      } finally {
        this.isLoading = false
      }
  },
}
</script>

<template>
  <div
    v-if="collection"
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
          </router-link>
        </p>
      </div>
      <div class="col-12 px-5">
        <h2>
          Timeless Love
        </h2>
        <p> Our Complete Collection </p>
      </div>
    </div>
    <collection-view :collection="collection" />
  </div>
  <div
    v-else
    class="container"
  >
    <h1>
      Loading...
    </h1>
    <p>
      Category: {{ $route.params.category }}
    </p>
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
  async beforeRouteUpdate(_, __, next) {
    this.collection = null
    this.collection = await client.getAllProducts()
    next()
  },
  data () {
    return {
      collection: null
    }
  },
  async mounted () {
    this.collection = await client.getAllProducts()
  },
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

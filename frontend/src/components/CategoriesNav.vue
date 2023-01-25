<template>
  <p
    data-testid="root-wo-attr"
    class="mt-5"
  >
    Categories.
  </p>
  <div
    v-bind="$attrs"
    data-testid="root-w-attr"
  >
    <h2 class="mb-5">
      popular categories.
    </h2>
    <div class="d-flex justify-content-center">
      <router-link :to="{ name: 'categories', params: { category: 'hats' }}">
        <button class="px-5 py-3 bg-white mx-1 text-uppercase"> Hats </button>
      </router-link>
      <router-link :to="{ name: 'categories', params: { category: 'jackets' }}">
        <button class="px-5 py-3 bg-white mx-1 text-uppercase"> Jackets </button>
      </router-link>
      <router-link :to="{ name: 'categories', params: { category: 'tshirts' }}">
        <button class="px-5 py-3 bg-white mx-1 text-uppercase"> T-shirts </button>
      </router-link>
      <router-link :to="{ name: 'categories', params: { category: 'bags' }}">
        <button class="px-5 py-3 bg-white mx-1 text-uppercase"> Bags </button>
      </router-link>
    </div>
  </div>
</template>
<script>
export default {

}
</script>


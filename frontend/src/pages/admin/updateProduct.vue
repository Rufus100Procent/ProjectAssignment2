<template>
  <div class="container">
    <div class="row">
      <div class="col-10 offset-1 col-lg-6 offset-lg-2">

        <div class="container px-3 text-left">

          <div class="row d-flex align-items-center pb-2">
            <div class="col-4">
              <label>Product: </label>
            </div>
            <div class="col-8">
              <select style="height: 32px" v-model="productId">
                <option v-for="product in products" :key="product.id" :value="product.id">{{ product.title }}</option>
              </select>
            </div>
          </div>

          <div class="row d-flex align-items-center pb-2">
            <div class="col-4">
              <label>Title: </label>
            </div>
            <div class="col-8">
              <input type="text" v-model="title" />
            </div>
          </div>
          <div class="row d-flex align-items-center pb-2">
            <div class="col-4">
              <label>Description: </label>
            </div>
            <div class="col-8">
              <input type="text" v-model="description" />
            </div>
          </div>
          <div class="row d-flex align-items-center">
            <div class="col-4">
              <label>Preview image: </label>
            </div>
            <div class="col-8">
              <input type="text" v-model="previewImage" />
            </div>
          </div>

          <div class="d-flex justify-content-end">
            <button class="mt-4 btn btn-success" @click="updateProduct"> Update product </button>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { getAllProducts, updateProduct } from '@/services/api-client'
export default {
  data() {
    return {
      products: [],
      productId: 0,
      title: "",
      description: "",
      previewImage: "",
    }
  },
  mounted() {
    console.log("mouuuuuuunted");
    getAllProducts()
      .then((res) => {
        console.log("res=", res);
        this.products = res;
      })
      .catch((err) => {
        console.log("err=", err);
        this.products = [];
      })
  },
  methods: {
    updateProduct() {
      updateProduct({ id: this.productId, title: this.title, description: this.description, previewImage: this.previewImage })
    }
  }
}
</script>
<style scoped>
  input, select {
    width: 100%;
    border-radius: 6px;
  }
  div {
    border-radius: 6px;
  }
</style>
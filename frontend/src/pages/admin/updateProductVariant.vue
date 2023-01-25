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
              <select @change="onProductChange" style="height: 32px" v-model="productId">
                <option v-for="product in products" :key="product.id" :value="product.id">{{ product.title }}</option>
              </select>
            </div>
          </div>
        </div>

        <div class="container px-3 text-left">
          <div class="row d-flex align-items-center pb-2">
            <div class="col-4">
              <label>Variant: </label>
            </div>
            <div class="col-8">
              <select style="height: 32px" v-model="variantId">
                <option v-for="variant in variants" :key="variant.id" :value="variant.id">{{ variant.title }}</option>
              </select>
            </div>
          </div>
        </div>
<!-- 
        <div class="container px-3 text-left">

          <div class="container-fluid mt-3 p-2 border">
            <div class="row mb-3 d-flex align-items-center" v-for="colorVariant, i in colorVariants" :key="i">
              <div class="col-10 offset-1 border">
                <div class="row">
                  <p><strong>Variant {{ i+1 }}:</strong></p><br/>
                  <div class="col-4">
                    <label>Color name: </label>
                  </div>
                  <div class="col-8">
                    <input v-model="colorVariants[i].colorName"/><br/>
                  </div>

                  <div class="col-12">
                    <div class="container-fluid mt-3 mb-2 p-2 border">
                      <div class="row d-flex align-items-center pb-1" v-for="image, j in colorVariants[i].images" :key="j">
                        <div class="col-4">
                          <label>Image {{ j+1 }} </label>
                        </div>
                        <div class="col-8">
                          <input style="border-color: #888" v-model="colorVariants[i].images[j]"/>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-4 offset-8 d-flex align-items-center justify-content-end">
                          <button class="mt-1 ml-4 py-1 btn btn-info text-white" @click="plusImage(i)"> Add image </button>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="col-12">
                    <div class="container-fluid mt-3 mb-2 p-2 border">
                      <div class="row d-flex align-items-center pb-1" v-for="size, j in colorVariants[i].sizes" :key="j">
                        <div class="col-4">
                          <label>Size {{ j+1 }} name </label>
                        </div>
                        <div class="col-8 pb-1">
                          <input style="border-color: #888" v-model="colorVariants[i].sizes[j].size"/>
                        </div>
                        <div class="col-4">
                          <label>Size {{ j+1 }} stock </label>
                        </div>
                        <div class="col-8 pb-4">
                          <input style="border-color: #888" v-model="colorVariants[i].sizes[j].stock"/>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-4 offset-8 d-flex align-items-center justify-content-end">
                          <button class="mt-1 ml-4 py-1 btn btn-info text-white" @click="plusSize(i)"> Add size </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-4 offset-8 d-flex align-items-center justify-content-end">
                <button class="mt-2 ml-4 btn btn-primary" @click="plus()"> Add colorVariant </button>
              </div>
            </div>

          </div>

          <div class="d-flex justify-content-end">
            <button class="mt-4 btn btn-success" @click="createProduct"> Create product </button>
          </div>

        </div> -->
      </div>
    </div>
  </div>
</template>
<script>
import { getAllProducts, getProductById, updateProduct } from '@/services/api-client'
export default {
  data() {
    return {
      products: [],
      variants: [],
      product: {},
      productId: 0,
      variantId: 0,
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
    updateProductVariant() {
      // updateProductVariant({ variantId: ..., productId: this.productId, title: this.title, description: this.description, previewImage: this.previewImage })
    },
    onProductChange(ev) {
      getProductById(this.productId)
        .then((res) => {
          this.product = res;
        })
        .catch((err) => {
          console.log("err=", err);
          this.product = {};
        })
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
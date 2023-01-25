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

          <div class="container-fluid mt-3 p-2 border">
            <div class="col-10 offset-1 border">
              <div class="row">
                <div class="col-4">
                  <label>Color name: </label>
                </div>
                <div class="col-8">
                  <input v-model="colorName"/><br/>
                </div>

                <div class="col-12">
                  <div class="container-fluid mt-3 mb-2 p-2 border">
                    <div class="row d-flex align-items-center pb-1" v-for="image, j in images" :key="j">
                      <div class="col-4">
                        <label>Image {{ j+1 }} </label>
                      </div>
                      <div class="col-8">
                        <input style="border-color: #888" v-model="images[j]"/>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-4 offset-8 d-flex align-items-center justify-content-end">
                        <button class="mt-1 ml-4 py-1 btn btn-info text-white" @click="plusImage()"> Add image </button>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="col-12">
                  <div class="container-fluid mt-3 mb-2 p-2 border">
                    <div class="row d-flex align-items-center pb-1" v-for="size, j in sizes" :key="j">
                      <div class="col-4">
                        <label>Size {{ j+1 }} name </label>
                      </div>
                      <div class="col-8 pb-1">
                        <input style="border-color: #888" v-model="sizes[j].size"/>
                      </div>
                      <div class="col-4">
                        <label>Size {{ j+1 }} stock </label>
                      </div>
                      <div class="col-8 pb-4">
                        <input style="border-color: #888" v-model="sizes[j].stock"/>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-4 offset-8 d-flex align-items-center justify-content-end">
                        <button class="mt-1 ml-4 py-1 btn btn-info text-white" @click="plusSize()"> Add size </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>

          <div class="d-flex justify-content-end">
            <button class="mt-4 btn btn-success" @click="addProductVariant"> Create variant </button>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { getAllProducts, addProductVariant } from '@/services/api-client'
export default {
  data() {
    return {
      products: [],
      product: {},
      productId: 0,
      colorName: "",
      images: [""],
      sizes: [ {} ]
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
    addProductVariant() {
      addProductVariant({ id: this.productId, colorName: this.colorName, images: this.images, sizes: this.sizes })
    },
    plusImage() {
      this.images.push("")
    },
    plusSize() {
      this.sizes.push({})
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
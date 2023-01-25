<template>
  <div class="container">
    <div class="row">
      <div class="col-10 offset-1 col-lg-6 offset-lg-2">
        <div class="container px-3 text-left">
          <div class="row d-flex align-items-center pb-2">
            <div class="col-4">
              <label>Category: </label>
            </div>
            <div class="col-8">
              <select style="height: 32px" v-model="category">
                <option value="hats">Hats</option>
                <option value="jackets">Jackets</option>
                <option value="tshirts">T-shirts</option>
                <option value="bags">Bags</option>
              </select>
            </div>
          </div>
        </div>
        <div class="container px-3 text-left">
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

        </div>
      </div>
    </div>
  </div>
</template>
<script>
/* 
{
    "title":"string",
    "description":"string",
    "previewImage":"string",
    "colorVariants":[
        {
            "colorName":"string",
            "images":[ "string" ],
            "sizes":[
                {
                    "size":"string",
                    "stock":"integer"
                }
            ]
        }
    ]
}
*/
import { createProduct } from '@/services/api-client'
export default {
  data() {
    return {
      category: "",
      title: "",
      description: "",
      previewImage: "",
      colorVariants: [ { images: [""], sizes: [ {} ] } ]
    }
  },
  methods: {
    createProduct() {
      console.log("colorVariants=", this.colorVariants);
      // this.$emit('formSubmitted', { name: this.name })
      createProduct({ category: this.category, title: this.title, description: this.description, previewImage: this.previewImage, colorVariants: this.colorVariants })
    },
    plus() {
      this.colorVariants.push({ images: [""], sizes: [ {} ] })
      // this.$emit('formSubmitted', { name: this.name })
    },
    plusImage(i) {
      this.colorVariants[i].images.push("")
      // this.$emit('formSubmitted', { name: this.name })
    },
    plusSize(i) {
      this.colorVariants[i].sizes.push({})
      // this.$emit('formSubmitted', { name: this.name })
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
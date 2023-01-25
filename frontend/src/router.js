/*
* src/router.js
*
* Based on -> https://github.com/gautemo/Vue-guard-routes-with-Firebase-Authentication
*/
import { createRouter, createWebHistory } from 'vue-router';   // map from 'vue-router' once Vite has it (this is only in one place, so no big deal)
import home from './pages/home.vue';
import aboutsalt from './pages/about-salt.vue';
import AltHeader from './components/AltHeader.vue';
import SaltHeader from './components/SaltHeader.vue';
import collection from '@/pages/collection.vue';
import products from '@/pages/products.vue';
import categories from '@/pages/categories.vue';
import cartpage from '@/pages/cart-page.vue';
import adminPage from '@/pages/admin/adminPage.vue';
import createProduct from '@/pages/admin/createProduct.vue';
import updateProduct from '@/pages/admin/updateProduct.vue';
import updateProductVariant from '@/pages/admin/updateProductVariant.vue';
import addProductVariant from '@/pages/admin/addProductVariant.vue';


const routes = [
  // {
  //   path: '/',
  //   components: {
  //     default: home,
  //     header: SaltHeader,
  //   },
  //   name: 'home'
  // },
  {
    path: '/collection',
    components: {
      default: collection,
      header: SaltHeader,
    },
    name: 'collection'
  },
  {
    path: '/',
    components: {
      default: home,
      header: SaltHeader,
    },
    name: 'home'
  },
  {
    path: '/about',
    components: {
      default: aboutsalt,
      header: AltHeader,
    },
    name: 'about-salt'
  },
  {
    path: '/products/:id',
    components: {
      default: products,
      header: SaltHeader,
    },
    name: 'products'
  },
  {
    path: '/categories/:category',
    components: {
      default: categories,
      header: SaltHeader,
    },
    name: 'categories'
  },
  {
    path: '/cart',
    components: {
      default: cartpage,
      header: SaltHeader,
    },
    name: 'cartpage'
  },
  {
    path: '/admin',
    components: {
      default: adminPage,
      header: AltHeader,
    },
    name: 'admin-page'
  },
  {
    path: '/admin/create-product',
    components: {
      default: createProduct,
      header: AltHeader,
    },
    name: 'create-product'
  },
  {
    path: '/admin/update-product',
    components: {
      default: updateProduct,
      header: AltHeader,
    },
    name: 'update-product'
  },
  {
    path: '/admin/update-product-variant',
    components: {
      default: updateProductVariant,
      header: AltHeader,
    },
    name: 'update-product-variant'
  },
  {
    path: '/admin/add-product-variant',
    components: {
      default: addProductVariant,
      header: AltHeader,
    },
    name: 'add-product-variant'
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});


router.beforeEach(() => {
  // Scroll page to top on every route change
  setTimeout(() => {
    window.scrollTo(0, 0);
  }, 100);
});

export default router
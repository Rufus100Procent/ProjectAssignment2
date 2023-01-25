import { createStore } from 'vuex'
import { getCart, setCart, buyCart, resetCart } from "../services/api-client"

const storageKey = 'salt-merch-cart'

// Create a new store instance.
const store = createStore({
  state () {
    const cart = JSON.parse(localStorage.getItem(storageKey) || '[]')
    return {
      cart
    }
  },
  mutations: {
    ADD_ITEM (state, newItem) {
      console.log("store mutation ADD_ITEM");
      console.log("state.cart=", state.cart);
      let prev_item = state.cart.find(el => el.productId == newItem.productId && el.color == newItem.color && el.size == newItem.size )
      console.log("prev_item=", prev_item);
      if (!prev_item)
        state.cart.push({ ...newItem, quantity: 1 })
      else
        prev_item.quantity++

      // state.cart.sort(
      //   (a, b) =>
      //     a.title.localeCompare(b.title) +
      //     a.color.localeCompare( b.color ) +
      //     a.size.localeCompare( b.size) )

      localStorage.setItem(storageKey, JSON.stringify(state.cart))
    },
    REMOVE_ITEM (state, newItem) {
      console.log("store mutation REMOVE_ITEM");
      let prev_item = state.cart.find(el => el.productId == newItem.productId && el.color == newItem.color && el.size == newItem.size )
      console.log("prev_item=", prev_item);
      if (!prev_item)
       return
      else
        prev_item.quantity--
      const rest = state.cart.filter(el => el.productId != newItem.productId || el.color != newItem.color || el.size != newItem.size)
      if (!prev_item.quantity)
        state.cart = [ ...rest ]
      // else
      //   state.cart = [ ...rest, prev_item ]
      localStorage.setItem(storageKey, JSON.stringify(state.cart))
    },
    SET_ITEMS (state, items) {
      console.log("store mutation SET_ITEMS");
      state.cart = items
      localStorage.setItem(storageKey, JSON.stringify(state.cart))
    }
  },
  actions: {
    async addItem ({ commit }, newItem) {
      console.log("store action addItem");
      try {
        const res = await setCart(1, newItem, 'add')
        console.log("addItem: setCart() THEN res=", res)
        commit('ADD_ITEM', newItem)
        return true
      } catch (err) {
        console.log("addItem: setCart() catch err=", err)
        return false
      }
    },
    removeItem ({ commit }, newItem) {
      console.log("store action removeItem");
      setCart(1, newItem, 'remove')
        .then((res) => {
          console.log("removeItem: setCart() THEN res=", res);
          commit('REMOVE_ITEM', newItem)
        })
        .catch((err) => {
          console.log("removeItem: setCart() catch err=", err);
        })
    },
    setItems ({ commit }, items) {
      commit('SET_ITEMS', items)
    },
    buyCart ({ commit}, id) {
      buyCart(id)
        .then((res) => {
          console.log("buyCart res=", res);
          commit('SET_ITEMS', [])
        })
        .catch((err) => {
          console.log("buyCart err=", err);
        })
    },
    resetCart ({ commit}, id) {
      console.log("store action resetCart ..... calling resetCart()");
      resetCart(id)
        .then((res) => {
          console.log("resetCart res=", res);
          commit('SET_ITEMS', [])
        })
        .catch((err) => {
          console.log("resetCart err=", err);
        })
    },
    getCart({ commit }, id) {
      getCart(id)
        .then((res) => {
          console.log("getCart: THEN res=", res);
          commit('SET_ITEMS', res)
        })
        .catch((err) => {
          console.log("getCart: catch err=", err);
        })
    }
  },
  modules: {},
})

export default store

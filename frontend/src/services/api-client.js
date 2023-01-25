import categories from '../assets/db/categories.json'
import { getHexColor } from '../utilities.js'

const serverSuites = [
  {
    port: '3001',
    categoriesEndpoint: 'categories',
    baseUrlPrefix: '/api/'
  },
  {
    port: '8099',
    categoriesEndpoint: 'products',
    baseUrlPrefix: '/api/v1/'
  }
]
let ss
ss = serverSuites[0]
ss = serverSuites[1]
let port = ss.port

const baseUrl = () => {
  const urlSearchParams = new URLSearchParams(window.location.search);
  const params = Object.fromEntries(urlSearchParams.entries());
  port = params.port || port
  return `http://localhost:${port}${ss.baseUrlPrefix}`
}
const handleRes = async (res, transform, extra) => {
  const _json = await res.json()
  console.log('BEFORE _json=', _json);
  if (res.ok) {
    if (!transform)
      return _json
    return transform(_json, extra)
  }
  throw new Error((await res.json()).message)
}

export const getProductById = id => fetch(`${baseUrl()}products/${id}`).then(res => handleRes(res, transformProduct))
export const getAllProducts = () => fetch(`${baseUrl()}products`).then(res => handleRes(res))
export const getProductsByCategory = category => fetch(`${baseUrl()}${ss.categoriesEndpoint}/${category}`).then(res => handleRes(res, transformProductsByCategory, category))

export const createProduct = product => fetch(`${baseUrl()}products/${product.category}`, {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(product)
}).then(handleResText)

export const updateProduct = product => fetch(`${baseUrl()}products/${product.id}`, {
  method: 'PUT',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(product)
}).then(handleResText)

export const addProductVariant = payload => fetch(`${baseUrl()}products/${payload.id}/variants`, {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(payload)
}).then(handleResText)

const transformProduct = (json) => {
  json.colorVariants = json.colorVariants?.length ? json.colorVariants.map((el) => {
    el.colorhex = getHexColor(el.colorName)
    return el
  }) : [ ]
  console.log('AFTER json=', json);
  return json
}
const transformProductsByCategory = (json, category) => {
  return { category, description: categories[category].description, title: categories[category].title, products: json };
}

export const buyCart = id => fetch(`${baseUrl()}carts/${id}?buyout=true`, { method: 'DELETE' }).then(handleResText)

const handleResText = async res => {
  if (res.ok)
    return res
  throw new Error('Unknown error')
}

export const resetCart = id => fetch(`${baseUrl()}carts/${id}`, { method: 'DELETE' }).then(handleResText)
export const getCart = id => fetch(`${baseUrl()}carts/${id}`).then(res => handleRes(res))
export const setCart = (id, item, action) => fetch(`${baseUrl()}carts/${id}?action=${action}`, {
  method: 'PATCH',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify(item)
}).then(handleResText)
export const setPort = newPort => port = newPort

const allTogether = {
  // getProductBySlug,
  getProductById,
  getAllProducts,
  getProductsByCategory
}

export default allTogether
package se.jensenyh.javacourse.saltmerch.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jensenyh.javacourse.saltmerch.backend.model.ColorVariant;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository repository) {
        this.productRepository = repository;
    }

    public List<Product> getProducts() {

        return productRepository.selectAll();
    }

    public List<Product> getProductByName(String category) {

        return productRepository.selectAllOfCategory(category);
    }

    public Object getProductById(int id) {

        return productRepository.getEntireProduct(id);
    }

    public Product addProduct(Product product, String category) {

        return productRepository.insertProductAndProps(product, category);
    }

    public int updateProduct(int id, Product product) {

        if (product.title == null || product.title.isEmpty() || product.description == null
                || product.description.isEmpty())
            return 0;

        return productRepository.updateProductMeta(id, product);
    }

    public ColorVariant addColorVariant(int id, ColorVariant co) {

        return productRepository.addVariant(id, co);
    }

    public int restockSizeVariant(int id,String size, String color, int quantity) {

        if (size == null || size.isEmpty() || color == null
                || color.isEmpty() || quantity == 0)
            return -2;

        return productRepository.restockSize(id, size, color, quantity);
    }

    public int deleteProduct(int id) {

        if (id > getProducts().get(getProducts().size() -1).id)
            return -2;

        return productRepository.deleteProduct(id);
    }

    public int deleteColorVariant(int productId, String colorV) {

        return productRepository.deleteVariant(productId, colorV);
    }
}
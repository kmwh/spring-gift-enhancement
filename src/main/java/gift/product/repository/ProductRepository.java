package gift.product.repository;

import gift.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


//    long createProduct(Product product);
//    List<Product> findAllProducts();
//    Product findProductById(Long id);
//    void updateProduct(Product product);
//    void deleteProduct(Long id);
}

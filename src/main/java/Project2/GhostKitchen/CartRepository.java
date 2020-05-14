package Project2.GhostKitchen;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findAll();
}
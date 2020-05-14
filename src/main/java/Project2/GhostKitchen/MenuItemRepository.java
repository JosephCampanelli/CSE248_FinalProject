package Project2.GhostKitchen;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAll();
    List<MenuItem> findAllByRestaurantID(Long RestaurantID);
}
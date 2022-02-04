package guru.springframework.sfgrecipeproject.repositories;

import guru.springframework.sfgrecipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}

package guru.springframework.sfgrecipeproject.services;

import guru.springframework.sfgrecipeproject.domain.UnitOfMeasure;

import java.util.List;

public interface UnitOfMeasureService {
    List<UnitOfMeasure> findAll();
    UnitOfMeasure save(UnitOfMeasure uom);
    UnitOfMeasure findByDescription(String description);
    UnitOfMeasure findById(Long id);
}

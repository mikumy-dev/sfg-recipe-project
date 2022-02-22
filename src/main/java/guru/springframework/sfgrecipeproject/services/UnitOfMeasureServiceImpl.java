package guru.springframework.sfgrecipeproject.services;

import guru.springframework.sfgrecipeproject.domain.UnitOfMeasure;
import guru.springframework.sfgrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public List<UnitOfMeasure> findAll() {
        List<UnitOfMeasure> unitOfMeasureList = new ArrayList<>();
        unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> unitOfMeasureList.add(unitOfMeasure));
        return unitOfMeasureList;
    }

    @Override
    public UnitOfMeasure save(UnitOfMeasure uom) {
        return unitOfMeasureRepository.save(uom);
    }

    @Override
    public UnitOfMeasure findByDescription(String description) {
        return unitOfMeasureRepository.findByDescription(description).orElse(null);
    }

    @Override
    public UnitOfMeasure findById(Long id) {
        return unitOfMeasureRepository.findById(id).orElse(null);
    }
}

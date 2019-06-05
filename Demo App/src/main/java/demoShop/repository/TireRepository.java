package demoShop.repository;

import demoShop.parts.Tire;

public interface TireRepository {
    Iterable<Tire> findAll();

    void deleteTire(Long tireId);

    Tire findTire(Long id);

    Tire addTire(Tire tire);
}

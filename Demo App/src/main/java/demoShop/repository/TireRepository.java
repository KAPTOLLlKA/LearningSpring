package demoShop.repository;

import demoShop.parts.Tire;

import java.util.Collection;

public interface TireRepository {
    Collection<Tire> findAll();

    void deleteTire(Long tireId);

    Tire findTire(Long id);

    int getTireCount(Long id);

    Tire addTire(Tire tire);
}

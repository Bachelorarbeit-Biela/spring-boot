package de.biela.migraine.repository;

import de.biela.migraine.model.entity.Migraine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MigraineRepository extends JpaRepository<Migraine, UUID> {
}

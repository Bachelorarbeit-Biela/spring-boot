package de.biela.migraine.service.impl;

import de.biela.migraine.model.dto.MigraineDto;
import de.biela.migraine.model.entity.Migraine;
import de.biela.migraine.repository.MigraineRepository;
import de.biela.migraine.service.MigraineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class TestServiceImpl implements MigraineService {
    private final MigraineRepository migraineRepository;
    public TestServiceImpl(MigraineRepository migraineRepository) {
        this.migraineRepository = migraineRepository;
    }
    @Override
    public MigraineDto getMigraineById(final UUID id) {
        Optional <Migraine> migraine = migraineRepository.findById(id);
        return migraine.map(mig -> new MigraineDto(mig.getId(),mig.getDate(),mig.getDescription(),mig.getPainSeverity(),mig.getCreationTimestamp(),mig.getModificationTimestamp(),null)).orElse(null);
    }
    @Override
    public MigraineDto updateMigraineById(final UUID id, final MigraineDto updatedMigraine){
        try {
            Optional<Migraine> existingMigraine = migraineRepository.findById(id);
            if (existingMigraine.isPresent()) {
                Migraine tempMigraine = existingMigraine.get();
                if (updatedMigraine.date() != null) {
                    tempMigraine.setDate(updatedMigraine.date());
                    tempMigraine.setModificationTimestamp(LocalDateTime.now());
                }
                if (updatedMigraine.description() != null) {
                    tempMigraine.setDescription(updatedMigraine.description());
                    tempMigraine.setModificationTimestamp(LocalDateTime.now());
                }
                if (updatedMigraine.painSeverity() != null) {
                    tempMigraine.setPainSeverity(updatedMigraine.painSeverity());
                    tempMigraine.setModificationTimestamp(LocalDateTime.now());
                }
                Migraine temp = migraineRepository.save(tempMigraine);
                return new MigraineDto(temp.getId(),temp.getDate(),temp.getDescription(),temp.getPainSeverity(),temp.getCreationTimestamp(),temp.getModificationTimestamp(), temp.getDrugIntakeList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Ungültige Argumente für die Migräne update.");
        }
        return updatedMigraine;
    }
    @Override
    public MigraineDto createMigraineById(UUID id, MigraineDto createMigraine) {
        try {
            Migraine migraine = new Migraine(createMigraine.date(),createMigraine.description(),createMigraine.painSeverity(), createMigraine.creationTimestamp(),createMigraine.modificationTimestamp());
            migraine =migraineRepository.save(migraine);
            return new MigraineDto(migraine.getId(),migraine.getDate(),migraine.getDescription(),migraine.getPainSeverity(),migraine.getCreationTimestamp(),migraine.getModificationTimestamp(), migraine.getDrugIntakeList());
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Ungültige Argumente für die Erstellung von Migräne.");
        }

    }
    @Override
    public String deleteMigraineById(UUID id) {

        migraineRepository.deleteById(id);
        return "Migräne wurde gelöscht";
    }


}

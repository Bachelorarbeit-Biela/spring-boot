package de.biela.migraine.service.impl;

import de.biela.migraine.model.dto.DrugIntakeDto;
import de.biela.migraine.model.entity.DrugIntake;
import de.biela.migraine.repository.DrugIntakeRepository;
import de.biela.migraine.service.DrugIntakeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class DrugIntakeServiceImpl implements DrugIntakeService {
    private final DrugIntakeRepository drugIntakeRepository;

    public DrugIntakeServiceImpl(DrugIntakeRepository drugIntakeRepository) {
        this.drugIntakeRepository = drugIntakeRepository;
    }

    @Override
    public DrugIntakeDto getDrugIntakeById(UUID id) {

            Optional<DrugIntake> drugIntake = drugIntakeRepository.findById(id);
            return drugIntake.map(drug -> new DrugIntakeDto(drug.getId(),drug.getDrug(),drug.getAmountEntity(),drug.getAmount(),drug.getTakeTimestamp(),drug.getCreationTimestamp(),drug.getModificationTimestamp(),drug.getMigraine())).orElse(null);

    }
    @Override
    public DrugIntakeDto updateDrugIntakeById(UUID id, DrugIntakeDto updatedDrugIntake) {

            Optional<DrugIntake> existingDrugIntake = drugIntakeRepository.findById(id);
            if (existingDrugIntake.isPresent()) {
                DrugIntake tempDrugIntake = existingDrugIntake.get();
                if (updatedDrugIntake.drug() != null) {
                    tempDrugIntake.setDrug(updatedDrugIntake.drug());
                    tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
                }
                if (updatedDrugIntake.amountEntity() != null) {
                    tempDrugIntake.setAmountEntity(updatedDrugIntake.amountEntity());
                    tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
                }
                if (updatedDrugIntake.amount() != null) {
                    tempDrugIntake.setAmount(updatedDrugIntake.amount());
                    tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
                }
                if (updatedDrugIntake.takeTimestamp() != null) {
                    tempDrugIntake.setTakeTimestamp(updatedDrugIntake.takeTimestamp());
                    tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
                }
                if (updatedDrugIntake.migraine() != null) {
                    tempDrugIntake.setMigraine(updatedDrugIntake.migraine());
                    tempDrugIntake.setModificationTimestamp(LocalDateTime.now());
                }
                DrugIntake temp = drugIntakeRepository.save(tempDrugIntake);
                return new DrugIntakeDto(temp.getId(),temp.getDrug(),temp.getAmountEntity(),temp.getAmount(),temp.getTakeTimestamp(),temp.getCreationTimestamp(),temp.getModificationTimestamp(),temp.getMigraine());
            }
        return updatedDrugIntake;
    }
    @Override
    public DrugIntakeDto createDrugIntakeById(UUID id, DrugIntakeDto createDrugIntake) {
        try {
            DrugIntake drugIntake = new DrugIntake(createDrugIntake.drug(),createDrugIntake.amountEntity(),createDrugIntake.amount(), createDrugIntake.takeTimestamp(),createDrugIntake.creationTimestamp(),createDrugIntake.modificationTimestamp(),createDrugIntake.migraine());
            drugIntake =  drugIntakeRepository.save(drugIntake);
            return new DrugIntakeDto(drugIntake.getId(),drugIntake.getDrug(),drugIntake.getAmountEntity(),drugIntake.getAmount(),drugIntake.getTakeTimestamp(),drugIntake.getCreationTimestamp(),drugIntake.getModificationTimestamp(),drugIntake.getMigraine());
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Ungültige Argumente für die Erstellung von DrugIntake.");
        }

    }
    @Override
    public String deleteDrugIntakeById(UUID id) {
            drugIntakeRepository.deleteById(id);
            return "DrugIntake wurde gelöscht";
    }
}

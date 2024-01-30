package de.biela.migraine.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity(name = "DrugIntake")
@Table(name = "drugintake")
public class DrugIntake {
    public enum Drug {
        IBU, TRIPTAN, PARACETAMOL
    }
    public enum AmountEntity {
        GRAMS, PIECE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "drug")
    private Drug drug;
    @Column(name = "amountEntity")
    private AmountEntity amountEntity;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "takeTimestamp")
    private LocalDateTime takeTimestamp;
    @Column(name = "creationTimestamp")
    private LocalDateTime creationTimestamp;
    @Column(name = "modificationTimestamp")
    private LocalDateTime modificationTimestamp;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "migraineId")
    private Migraine migraine;

    public DrugIntake() {
    }

    public DrugIntake(Drug drug, AmountEntity amountEntity, BigDecimal amount,
                      LocalDateTime takeTimestamp, LocalDateTime creationTimestamp,
                      LocalDateTime modificationTimestamp,Migraine migraine) {
        this.drug = drug;
        this.amountEntity = amountEntity;
        this.amount = amount;
        this.takeTimestamp = takeTimestamp;
        this.creationTimestamp = creationTimestamp;
        this.modificationTimestamp = modificationTimestamp;
        this.migraine = migraine;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public AmountEntity getAmountEntity() {
        return amountEntity;
    }

    public void setAmountEntity(AmountEntity amountEntity) {
        this.amountEntity = amountEntity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTakeTimestamp() {
        return takeTimestamp;
    }

    public void setTakeTimestamp(LocalDateTime takeTimestamp) {
        this.takeTimestamp = takeTimestamp;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public LocalDateTime getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(LocalDateTime modificationTimestamp) {this.modificationTimestamp = modificationTimestamp;}

    public Migraine getMigraine() {
        return migraine;
    }

    public void setMigraine(Migraine migraine) {        this.migraine = migraine;    }

    @Override
    public String toString() {
        return "\nDrugIntake{\n" +
                "id=" + id +
                ", \ndrug=" + drug +
                ", \namountEntity=" + amountEntity +
                ", \namount=" + amount +
                ", \ntakeTimestamp=" + takeTimestamp +
                ", \ncreationTimestamp=" + creationTimestamp +
                ", \nmodificationTimestamp=" + modificationTimestamp +
                '}';
    }
}

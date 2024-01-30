package de.biela.migraine.controller;

import de.biela.migraine.model.dto.MigraineDto;
import de.biela.migraine.service.MigraineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/migraine")
public class MigraineController {
    private final MigraineService migraineService;
    public MigraineController(MigraineService migraineService) {
        this.migraineService = migraineService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<MigraineDto> getMigraineById(@PathVariable(value = "id") final String id) {
        return ResponseEntity.ok(migraineService.getMigraineById(UUID.fromString(id)));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<MigraineDto> updateMigraineById(@PathVariable(value = "id") final String id,
                                                          @RequestBody MigraineDto updatedMigraine)
    {
        return ResponseEntity.ok(migraineService.updateMigraineById(UUID.fromString(id), updatedMigraine));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MigraineDto> createMigraineById(@PathVariable(value = "id") final String id, @RequestBody MigraineDto createMigraine) {
        return ResponseEntity.ok(migraineService.createMigraineById(UUID.fromString(id), createMigraine));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteMigraineById(@PathVariable(value = "id") final String id) {
        return ResponseEntity.ok(migraineService.deleteMigraineById(UUID.fromString(id)));
    }




}

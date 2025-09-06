package com.cmed.Prescriptions.Controller;

import com.cmed.Prescriptions.Entity.Prescription;
import com.cmed.Prescriptions.Repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/API/v1")
public class PrescriptionApiController {

    @Autowired
    private PrescriptionRepo repo;

    @GetMapping("/prescription")
    public List<Prescription> getPrescriptions() {
        return repo.findAll();
    }
}

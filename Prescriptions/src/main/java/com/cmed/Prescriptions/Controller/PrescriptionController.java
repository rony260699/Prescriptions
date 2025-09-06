package com.cmed.Prescriptions.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.cmed.Prescriptions.Entity.Prescription;
import com.cmed.Prescriptions.Repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PrescriptionController {


    @Autowired
    PrescriptionRepo repo;


    @GetMapping("/")
    public String viewHomePage(
//            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Model model) {


        /*
        if (startDate == null || endDate == null) {
            LocalDate now = LocalDate.now();
            startDate = now.withDayOfMonth(1);
            endDate = now.withDayOfMonth(now.lengthOfMonth());
        }

        List<Prescription> prescriptions = repo.findByDateRange(startDate, endDate);
        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
*/

        model.addAttribute("prescriptions", repo.findAll());
        return "index";
    }


    @GetMapping("/prescription/new")
    public String newPrescriptionForm(Model model) {
        Prescription newPres = new Prescription();
        model.addAttribute("prescription", newPres);
        return "prescription";
    }


    @PostMapping("/prescription/save")
    public String savePrescription(@ModelAttribute("prescription") Prescription prescription){
        if(prescription.getCreatedAt() == null){
            prescription.setCreatedAt(LocalDateTime.now());
        }
        repo.save(prescription);
        return "redirect:/";
    }

    @GetMapping("/prescription/edit/{id}")
    public String editPrescription(@PathVariable Long id, Model model) {
        Prescription pres = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid prescription Id:" + id));
        model.addAttribute("prescription", pres);
        return "prescription"; // reuse same form
    }


    @PostMapping("/prescription/delete/{id}")
    public String deletePrescription(@PathVariable("id") Long id, RedirectAttributes ra) {
        Prescription prescription = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid prescription Id:" + id));
        repo.delete(prescription);
        ra.addFlashAttribute("success", "Prescription deleted successfully!");
        return "redirect:/";
    }

    @GetMapping("/reports")
    public String viewReports(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Model model) {

        if (startDate == null || endDate == null) {
            LocalDate now = LocalDate.now();
            startDate = now.withDayOfMonth(1);
            endDate = now.withDayOfMonth(now.lengthOfMonth());
        }

        List<Object[]> dayWiseData = repo.getDayWisePrescriptionCount(startDate, endDate);
        model.addAttribute("dayWiseData", dayWiseData);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "reports";
    }




}

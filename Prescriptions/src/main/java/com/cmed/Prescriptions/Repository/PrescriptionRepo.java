package com.cmed.Prescriptions.Repository;

import com.cmed.Prescriptions.Entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {
    @Query("SELECT p FROM Prescription p WHERE p.prescriptionDate BETWEEN :startDate AND :endDate ORDER BY p.prescriptionDate DESC")
    List<Prescription> findByDateRange(@Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate);

    @Query("SELECT DATE(p.prescriptionDate) as date, COUNT(p) as count FROM Prescription p " +
            "WHERE p.prescriptionDate BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(p.prescriptionDate) ORDER BY date")
    List<Object[]> getDayWisePrescriptionCount(@Param("startDate") LocalDate startDate,
                                               @Param("endDate") LocalDate endDate);
}

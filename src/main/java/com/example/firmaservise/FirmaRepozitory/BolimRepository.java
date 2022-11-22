package com.example.firmaservise.FirmaRepozitory;


import com.example.firmaservise.Entity.Bolim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BolimRepository extends JpaRepository<Bolim, Integer> {
    Optional<Bolim> findByBolimNomiAndFirma_Id(String bolimNomi, Integer firma_id);
}

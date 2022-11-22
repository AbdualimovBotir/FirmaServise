package com.example.firmaservise.FirmaRepozitory;

import com.example.firmaservise.Entity.FirmaEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "FirmaEntity")
public interface FirmaRepozitory extends JpaRepository<FirmaEntity,Integer> {
     boolean existsByFirmaNomi(String firmaNomi);
}
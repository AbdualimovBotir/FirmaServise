package com.example.firmaservise.FirmaRepozitory;

import com.example.firmaservise.Entity.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IshchiRepository extends JpaRepository<Ishchi, Integer> {
    boolean existsByTelNomer(String telNomer);
}

package com.example.firmaservise.ServiseFirma;


import com.example.firmaservise.Entity.Bolim;
import com.example.firmaservise.Entity.FirmaEntity;
import com.example.firmaservise.FirmaRepozitory.BolimRepository;
import com.example.firmaservise.FirmaRepozitory.FirmaRepozitory;
import com.example.firmaservise.Payload.ApiResponsFirma;
import com.example.firmaservise.Payload.BolimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BolimServis {
    @Autowired
    BolimRepository bolimRepository;

    @Autowired
    FirmaRepozitory firmaRepository;


    public ApiResponsFirma bolimPost(BolimDTO bolimDTO) {
        boolean b = firmaRepository.existsById(bolimDTO.getFirmaId());
        if (!b) return new ApiResponsFirma("Bunday id li firma mavjud emas!!!",false);
        Optional<Bolim> byBolimNomiAndFirma_id = bolimRepository.findByBolimNomiAndFirma_Id(bolimDTO.getBolimNomi(), bolimDTO.getFirmaId());
        if (byBolimNomiAndFirma_id.isPresent()) return new ApiResponsFirma("Bunday nomli bo'lim mavjud!!!",false);
        Bolim bolim = new Bolim();
        bolim.setBolimNomi(bolimDTO.getBolimNomi());
        bolim.setFirma(firmaRepository.findById(bolimDTO.getFirmaId()).get());
        bolimRepository.save(bolim);
        return new ApiResponsFirma("Ma'lumot bazaga saqlandi!!!",true);
    }

    public ApiResponsFirma bolimGet() {
        return new ApiResponsFirma(firmaRepository.findAll().toString(),true);
    }

    public ApiResponsFirma bolimGetId(Integer id) {
        Optional<FirmaEntity> byId = firmaRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponsFirma(id+"-idli malumot topilmadi!!!",false);
        return new ApiResponsFirma(firmaRepository.findById(id).toString(),true);
    }

    public ApiResponsFirma bolimdelet(Integer id) {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if(!byId.isPresent()) return new ApiResponsFirma(id+"-idli malumot topilmadi!!!",false);
        Bolim bolim = byId.get();
        bolimRepository.delete(bolim);
        FirmaEntity firma = byId.get().getFirma();
        firmaRepository.delete(firma);
        return new ApiResponsFirma("Malumot o'chirildi!!!",true);
    }
}

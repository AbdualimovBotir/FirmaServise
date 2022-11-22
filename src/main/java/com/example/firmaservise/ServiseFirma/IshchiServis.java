package com.example.firmaservise.ServiseFirma;

import com.example.firmaservise.Entity.Bolim;
import com.example.firmaservise.Entity.Ishchi;
import com.example.firmaservise.Entity.ManzilEntity;
import com.example.firmaservise.FirmaRepozitory.BolimRepository;
import com.example.firmaservise.FirmaRepozitory.IshchiRepository;
import com.example.firmaservise.FirmaRepozitory.ManzilRepozitory;
import com.example.firmaservise.Payload.ApiResponsFirma;
import com.example.firmaservise.Payload.IshchiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IshchiServis {
    @Autowired
    IshchiRepository ishchiRepository;

    @Autowired
    ManzilRepozitory manzilRepository;

    @Autowired
    BolimRepository bolimRepository;

    public ApiResponsFirma ishchiPost(IshchiDTO ishchiDTO) {
        boolean b = ishchiRepository.existsByTelNomer(ishchiDTO.getTelNomer());
        if(b) return new ApiResponsFirma("Bunday telNomerli ishchi mavjud ekan!!!",false);
        boolean b1 = bolimRepository.existsById(ishchiDTO.getBolimId());
        if (!b1) return new ApiResponsFirma("Bunday id li bolim mavjud emas!!!",false);
        Ishchi ishchi = new Ishchi();
        ishchi.setFish(ishchiDTO.getFish());
        if(ishchiDTO.getTelNomer().length()==13 || ishchiDTO.getTelNomer().length()==9 ){
            ishchi.setTelNomer(ishchiDTO.getTelNomer());
        }
        else {
            return new ApiResponsFirma("Telnomer xato ketdi!!!",false);
        }
        ishchi.setBolim(bolimRepository.findById(ishchiDTO.getBolimId()).get());
        ManzilEntity manzil = new ManzilEntity();
        manzil.setViloyat(ishchiDTO.getVil());
        manzil.setTuman(ishchiDTO.getTum());
        manzil.setKucha(ishchiDTO.getKucha());
        manzil.setUyRaqami(Integer.valueOf(ishchiDTO.getUyRaqam()));
        manzilRepository.save(manzil);
        ishchi.setManzil(manzil);
        ishchiRepository.save(ishchi);
        return new ApiResponsFirma("Ma'lumot bazaga saqlandi!!!",true);
    }

    public ApiResponsFirma ishchiGet() {
        return new ApiResponsFirma(ishchiRepository.findAll().toString(),true);
    }

    public ApiResponsFirma ishchigetId(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponsFirma(id+"-idli malumot topilmadi!!!",false);
            return new ApiResponsFirma(ishchiRepository.findById(id).toString(),true);
    }

    public ApiResponsFirma ishchidelet(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if(!byId.isPresent()) return new ApiResponsFirma(id+"-idli malumot topilmadi!!!",false);
        Ishchi ishchi = byId.get();
        ishchiRepository.delete(ishchi);
        ManzilEntity manzil = byId.get().getManzil();
        manzilRepository.delete(manzil);
        Bolim bolim = byId.get().getBolim();
        bolimRepository.delete(bolim);
        return new ApiResponsFirma("Malumot o'chirildi!!!",true);
    }
}

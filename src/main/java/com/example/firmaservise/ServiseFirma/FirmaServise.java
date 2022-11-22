package com.example.firmaservise.ServiseFirma;

import com.example.firmaservise.Entity.FirmaEntity;
import com.example.firmaservise.Entity.ManzilEntity;
import com.example.firmaservise.FirmaRepozitory.FirmaRepozitory;
import com.example.firmaservise.FirmaRepozitory.ManzilRepozitory;
import com.example.firmaservise.Payload.ApiResponsFirma;
import com.example.firmaservise.Payload.FirmaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class FirmaServise {
     @Autowired
      FirmaRepozitory firmaRepozitory;
      @Autowired
      ManzilRepozitory manzilRepozitory;
    public ApiResponsFirma addFirma(@RequestBody FirmaDto firmaDto){
        boolean b=firmaRepozitory.existsByFirmaNomi(firmaDto.getFirmaNomi());
        if(b)
          return new ApiResponsFirma("bunday firma nomi allaqachon ro'yhatdan o'tgan",true);
        FirmaEntity firmaEntity=new FirmaEntity();
        firmaEntity.setFirmaNomi(firmaDto.getFirmaNomi());
        firmaEntity.setDriktorNomi(firmaDto.getDriktorNomi());
         ManzilEntity manzilEntity=new ManzilEntity();
         manzilEntity.setViloyat(firmaDto.getViloyat());
         manzilEntity.setTuman(firmaDto.getTuman());
         manzilEntity.setKucha(firmaDto.getKucha());
         manzilEntity.setUyRaqami(firmaDto.getUyRaqami());
         manzilRepozitory.save(manzilEntity);
         firmaEntity.setManzilEntity(manzilEntity);
         firmaRepozitory.save(firmaEntity);
         return  new ApiResponsFirma("firma ma'lumotlari saqlandi",false);
    }
    public ApiResponsFirma readFirma() {
        List<FirmaEntity> list=firmaRepozitory.findAll();
        String ss="";
        for (FirmaEntity firmaEntity : list) {
            String[] matn=firmaEntity.toString().split(", ");
            for (String s : matn) {
                if (s.indexOf("(")>0){
                    s=s.substring(s.indexOf("(")+1);
                }
                if (s.indexOf(")")>0){
                    s=s.substring(0,s.indexOf(")"));
                }
                ss+=s+"\n";
            }
            ss+="\n";
        }
        return new ApiResponsFirma(ss,true);
    }
    public ApiResponsFirma readFirmaid(Integer id) {
        Optional<FirmaEntity> byId = firmaRepozitory.findById(id);
        if (byId.isPresent()){
            List<FirmaEntity> list=firmaRepozitory.findAll();
            for (FirmaEntity firmaEntity : list) {
                String[] matn=firmaEntity.toString().split(", ");
                String ss="";
                for (String s : matn) {
                    if (s.indexOf("(")>0){
                        s=s.substring(s.indexOf("(")+1);
                    }
                    if (s.indexOf(")")>0){
                        s=s.substring(0,s.indexOf(")"));
                    }
                    ss+=s+"\n";
                }
                if (firmaEntity.getId()==id) return new ApiResponsFirma(ss,true);
            }
        }
        return new ApiResponsFirma("Bazada bunday idli dasturchi mavjud emas!",false);
    }
    public ApiResponsFirma editFirma(Integer id, FirmaDto firmaDto){
        Optional<FirmaEntity> optionalFirma=firmaRepozitory.findById(id);
        Optional<ManzilEntity> byId = manzilRepozitory.findById(optionalFirma.get().getId());
        if(optionalFirma.isPresent()){
           FirmaEntity firma=optionalFirma.get();
           firma.setFirmaNomi(firmaDto.getFirmaNomi());
           firma.setDriktorNomi(firmaDto.getDriktorNomi());
           ManzilEntity manzilEntity = byId.get();
           manzilEntity.setViloyat(firmaDto.getViloyat());
           manzilEntity.setTuman(firmaDto.getTuman());
           manzilEntity.setKucha(firmaDto.getKucha());
           manzilEntity.setUyRaqami(firmaDto.getUyRaqami());
           manzilRepozitory.save(manzilEntity);
           firma.setManzilEntity(manzilEntity);
           firmaRepozitory.save(firma);
           return new ApiResponsFirma( "ma'lumot taxrirlandi",true);
        }
        return new ApiResponsFirma("bazada bunday id li xodim mavjud emas",false);
    }
    public ApiResponsFirma deletFirma(Integer id) {
        Optional<FirmaEntity> byId = firmaRepozitory.findById(id);
        if(!byId.isPresent()) return new ApiResponsFirma(id+"-idli malumot topilmadi!.",false);
        FirmaEntity firma = byId.get();
        firmaRepozitory.delete(firma);
        ManzilEntity manzil = byId.get().getManzilEntity();
        manzilRepozitory.delete(manzil);
        return new ApiResponsFirma("Malumot o'chirildi!!!",true);
    }
}

package com.example.firmaservise.FirmaController;


import com.example.firmaservise.Entity.FirmaEntity;
import com.example.firmaservise.FirmaRepozitory.ManzilRepozitory;
import com.example.firmaservise.Payload.ApiResponsFirma;
import com.example.firmaservise.Payload.FirmaDto;
import com.example.firmaservise.ServiseFirma.FirmaServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Firma")
public class FirmaController {
   @Autowired
    FirmaServise firmaServise;
   @Autowired
    ManzilRepozitory manzilRepozitory;
    @PostMapping("/joylash")
    public HttpEntity<?> joylash(@RequestBody FirmaDto firmaDto){
     ApiResponsFirma apiResponsFirma=firmaServise.addFirma(firmaDto);
     return ResponseEntity.status(apiResponsFirma.isXolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponsFirma.getXabar());
    }
    @GetMapping("/uqish")
    public HttpEntity<?>Firmauqish(){
     ApiResponsFirma apiResponse=firmaServise.readFirma();
     return ResponseEntity.status(apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @GetMapping("/uqishid/{id}")
    public  HttpEntity<?> Firmauqishid(@PathVariable Integer id){
     ApiResponsFirma apiResponse=firmaServise.readFirmaid(id);
     return ResponseEntity.status(apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @PutMapping("/taxrirlash/{id}")
    public HttpEntity<?> Firmataxrirlashid(@PathVariable Integer id, @RequestBody FirmaDto firmaDto){
     ApiResponsFirma apiResponsFirma=firmaServise.editFirma(id,firmaDto);
     return ResponseEntity.status(apiResponsFirma.isXolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponsFirma.getXabar());
    }
    @DeleteMapping("/uchirish/{id}")
    public HttpEntity<?> Firmauchirishid(@PathVariable Integer id){
     ApiResponsFirma apiResponsFirma=firmaServise.deletFirma(id);
     return ResponseEntity.status(apiResponsFirma.isXolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponsFirma.getXabar());
    }
}

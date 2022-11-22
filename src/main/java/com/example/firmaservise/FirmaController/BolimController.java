package com.example.firmaservise.FirmaController;


import com.example.firmaservise.Payload.ApiResponsFirma;
import com.example.firmaservise.Payload.BolimDTO;
import com.example.firmaservise.ServiseFirma.BolimServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bolimAPI")
public class BolimController {
    @Autowired
    BolimServis bolimServis;

    @PostMapping("/boliJoylash")
    public HttpEntity<?> post(@RequestBody BolimDTO bolimDTO){
        ApiResponsFirma firmaAPIresponse = bolimServis.bolimPost(bolimDTO);
        return ResponseEntity.status(firmaAPIresponse.isXolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("olishBolim")
    public HttpEntity<?> getBolim(){
        ApiResponsFirma firmaAPIresponse = bolimServis.bolimGet();
        return ResponseEntity.status(HttpStatus.OK).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("/olish/{id}")
    public HttpEntity<?> getbolimId(@PathVariable Integer id){
        ApiResponsFirma firmaAPIresponse = bolimServis.bolimGetId(id);
        return ResponseEntity.status(firmaAPIresponse.isXolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponsFirma firmaAPIresponse = bolimServis.bolimdelet(id);
        return ResponseEntity.status(firmaAPIresponse.isXolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
}

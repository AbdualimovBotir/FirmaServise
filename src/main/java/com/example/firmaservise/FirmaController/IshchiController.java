package com.example.firmaservise.FirmaController;


import com.example.firmaservise.Payload.ApiResponsFirma;
import com.example.firmaservise.Payload.IshchiDTO;
import com.example.firmaservise.ServiseFirma.IshchiServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ishchiAPI")
public class IshchiController {
    @Autowired
    IshchiServis ishchiServis;

    @PostMapping("/ishchiJoylash")
    public HttpEntity<?> post(@RequestBody IshchiDTO ishchiDTO){
        ApiResponsFirma firmaAPIresponse = ishchiServis.ishchiPost(ishchiDTO);
        return ResponseEntity.status(firmaAPIresponse.isXolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("olishIshchi")
    public HttpEntity<?> getIshchi(){
        ApiResponsFirma firmaAPIresponse = ishchiServis.ishchiGet();
        return ResponseEntity.status(HttpStatus.OK).body(firmaAPIresponse.getXabar());
    }
    @GetMapping("/olish/{id}")
    public HttpEntity<?> getbolimId(@PathVariable Integer id){
        ApiResponsFirma firmaAPIresponse = ishchiServis.ishchigetId(id);
        return ResponseEntity.status(firmaAPIresponse.isXolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponsFirma firmaAPIresponse = ishchiServis.ishchidelet(id);
        return ResponseEntity.status(firmaAPIresponse.isXolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(firmaAPIresponse.getXabar());
    }
}

package com.example.firmaservise.FirmaController;

import com.example.firmaservise.FirmaRepozitory.ManzilRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Manzil")
public class ManzilController {
    @Autowired
    ManzilRepozitory manzilRepozitory;
}

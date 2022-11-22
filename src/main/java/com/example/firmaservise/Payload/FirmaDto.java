package com.example.firmaservise.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FirmaDto {
    private String firmaNomi,driktorNomi,viloyat,tuman,kucha;
    public Integer uyRaqami;
}

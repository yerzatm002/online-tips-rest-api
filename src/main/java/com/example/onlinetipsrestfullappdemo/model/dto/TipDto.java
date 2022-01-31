package com.example.onlinetipsrestfullappdemo.model.dto;

import com.example.onlinetipsrestfullappdemo.model.Tip;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TipDto {
    private Long id;
    private LocalDateTime createdAt;
    private Double sum;

    public static TipDto from(Tip tip){
        TipDto tipDto = new TipDto();
        tipDto.setId(tip.getId());
        tipDto.setCreatedAt(tip.getCreatedAt());
        tipDto.setSum(tip.getSum());
        return tipDto;
    }
}

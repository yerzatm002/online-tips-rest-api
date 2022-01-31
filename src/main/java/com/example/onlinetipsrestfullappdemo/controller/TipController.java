package com.example.onlinetipsrestfullappdemo.controller;

import com.example.onlinetipsrestfullappdemo.model.Tip;
import com.example.onlinetipsrestfullappdemo.model.dto.TipDto;
import com.example.onlinetipsrestfullappdemo.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tip")
public class TipController {

    private final TipService tipService;

    @Autowired
    public TipController(TipService tipService) {
        this.tipService = tipService;
    }

    @PostMapping(value = "staff/{staffId}")
    public ResponseEntity<TipDto> createTip(@PathVariable Long staffId,
                                            @RequestBody TipDto tipDto){
        Tip tip = Tip.from(tipDto);
        LocalDateTime dateTime = LocalDateTime.now();
        tip.setCreatedAt(dateTime);
        Tip createdTip = tipService.createTip(staffId,tip);
        return new ResponseEntity<>(TipDto.from(createdTip), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TipDto> getTip(@PathVariable Long id){
        Tip tip = tipService.getTip(id);
        return new ResponseEntity<>(TipDto.from(tip), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TipDto>>getAll(){
        List<Tip> tipList = tipService.getAllTip();
        List<TipDto> tipDtoList = tipList.stream().map(TipDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(tipDtoList, HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TipDto> updateTip(@PathVariable Long id,
                                            @RequestBody TipDto tipDto){
        Tip tip = tipService.updateTip(Tip.from(tipDto), id);
        return new ResponseEntity<>(TipDto.from(tip), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<TipDto> deleteTip(@PathVariable Long id){
        Tip tip = tipService.deleteTip(id);
        return new ResponseEntity<>(TipDto.from(tip), HttpStatus.OK);
    }

}

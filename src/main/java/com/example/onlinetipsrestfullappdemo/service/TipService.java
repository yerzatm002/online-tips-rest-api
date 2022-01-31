package com.example.onlinetipsrestfullappdemo.service;

import com.example.onlinetipsrestfullappdemo.model.Staff;
import com.example.onlinetipsrestfullappdemo.model.Tip;
import com.example.onlinetipsrestfullappdemo.repository.TipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TipService {

    private final TipRepo tipRepo;
    private final StaffService staffService;

    @Autowired
    public TipService(TipRepo tipRepo, StaffService staffService) {
        this.tipRepo = tipRepo;
        this.staffService = staffService;
    }

    public Tip createTip(Long staffId,Tip tip){
        Staff staff = staffService.getStaff(staffId);
        tip.setStaff(staff);
        staff.addTip(tip);
        return tipRepo.save(tip);
    }

    public Tip getTip(Long id){
        return tipRepo.findById(id).get();
    }

    @Transactional
    public Tip updateTip(Tip tip, Long id){
        Tip updateTip = getTip(id);
        updateTip.setCreatedAt(tip.getCreatedAt());
        updateTip.setSum(tip.getSum());
        return updateTip;
    }

    public Tip deleteTip(Long id){
        Tip tip = getTip(id);
        Staff staff = staffService.getStaff(tip.getStaff().getId());
        staff.removeTip(tip);
        tipRepo.delete(tip);
        return tip;
    }

    public List<Tip> getAllTip(){
        return tipRepo.findAll();
    }
}

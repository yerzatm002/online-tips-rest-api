package com.example.onlinetipsrestfullappdemo.model;

import com.example.onlinetipsrestfullappdemo.model.dto.TipDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tip {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    private Double sum;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public static Tip from(TipDto tipDto){
        Tip tip = new Tip();
        tip.setId(tipDto.getId());
        tip.setSum(tipDto.getSum());
        tip.setCreatedAt(tipDto.getCreatedAt());
        return tip;
    }
}

package com.tez.kariyer.model.entity;

import com.tez.kariyer.model.entity.address.Il;
import com.tez.kariyer.model.entity.parameterTable.EducationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToOne
    protected String  educationStatus;
    protected String notOrt;
    protected String university;
    @OneToOne
    protected Il city;
    private String  fakulte;
    private String  bolum;
    private String  dil;

    private String startDate;
    private String finishDate;

    @OneToOne
    protected User user;
}

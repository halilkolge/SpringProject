package com.tez.kariyer.model.entity;

import com.tez.kariyer.model.entity.address.Ilce;
import com.tez.kariyer.model.entity.address.Ulke;
import com.tez.kariyer.model.entity.parameterTable.CompanySector;
import com.tez.kariyer.model.entity.parameterTable.Position;
import com.tez.kariyer.model.entity.parameterTable.WayOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firmsName;
    @OneToOne
    private Position position;

    private Date startDate;
    private Date finishDate;
    private Byte workStatus;

    @OneToOne
    protected CompanySector companySector;

    @OneToOne
    protected WayOfWork wayOfWork;

    @OneToOne
    protected Ilce ilce;
    @OneToOne
    protected User user;

    private String jopDescription;

    private String web;

}

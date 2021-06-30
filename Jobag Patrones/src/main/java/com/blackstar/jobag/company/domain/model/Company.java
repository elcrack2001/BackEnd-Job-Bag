package com.blackstar.jobag.company.domain.model;

import com.blackstar.jobag.employeer.domain.model.Employeer;
import com.blackstar.jobag.sector.domain.model.Sector;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="companys")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 100)
    private String name;
    @NotNull
    @Length(max = 100)
    private String description;

    @NotNull
    @Length(max = 100)
    private String logo;

    private Long ruc;

    @NotNull
    @Length(max = 100)
    private String dirección;

    @OneToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employeer employeer;

    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sector_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Sector sector;


    public Company(Long id, String name, String description, String logo, Long ruc, String dirección, Employeer employeer, Sector sector) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.ruc = ruc;
        this.dirección = dirección;
        this.employeer = employeer;
        this.sector =sector;
    }

    public Company(){}

    public Long getId() {
        return id;
    }

    public Company setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Company setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public Company setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public Long getRuc() {
        return ruc;
    }

    public Company setRuc(Long ruc) {
        this.ruc = ruc;
        return this;
    }

    public String getDirección() {
        return dirección;
    }

    public Company setDirección(String dirección) {
        this.dirección = dirección;
        return this;
    }

    public Employeer getEmployeer() {
        return employeer;
    }

    public Company setEmployeer(Employeer employeer) {
        this.employeer = employeer;
        return this;
    }

    public Sector getSector() {
        return sector;
    }

    public Company setSector(Sector sector) {
        this.sector = sector;
        return this;
    }
}

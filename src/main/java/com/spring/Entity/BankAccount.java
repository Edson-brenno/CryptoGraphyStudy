package com.spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.Entity.Converter.CryptConverter;
import jakarta.persistence.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "tb_bank_account")
public class BankAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CryptConverter.class) // converter for the cryptography
    @Column(nullable = false, name = "name_bank")
    private String nameBank;
    @Convert(converter = CryptConverter.class) // converter for the cryptography
    @Column(nullable = false)
    private String agency;
    @Convert(converter = CryptConverter.class) // converter for the cryptography
    @Column(nullable = false, name = "account_number")
    private String number;
    @Column(nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "maintainer_id")
    private Maintainer maintainer;

    public BankAccount(){}

    public BankAccount(String name, String agency, String number, boolean isActive) {
        this.nameBank = name;
        this.agency = agency;
        this.number = number;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Maintainer getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(Maintainer maintainer) {
        this.maintainer = maintainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return isActive == that.isActive && Objects.equals(nameBank, that.nameBank) &&
                Objects.equals(agency, that.agency) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameBank, agency, number, isActive);
    }
}

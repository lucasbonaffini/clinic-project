package com.project.clinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;

    private Integer number;

    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getId(), address.getId()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getNumber(), address.getNumber()) && Objects.equals(getCity(), address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStreet(), getNumber(), getCity());
    }
}

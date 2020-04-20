package com.example.elwart.user.model;


import com.example.elwart.user.transport.Transport;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Entity
@Table(name="delegation")
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delegation_id")
    private Long id;
    @Column
    private String description;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeStart;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeStop;
    private Double travelDietAmount = 30D;
    private Integer breakfastNumber = 0;
    private Integer dinnerNumber = 0;
    private Integer supperNumber =0;
    @Enumerated(EnumType.STRING)
    private Transport transportType;
    private Double ticketPrice;
    private Integer autoCapacity;
    private Double km;
    private Double accommodationPrice;
    private Double otherTicketsPrice;
    private String otherOutlayDesc;
    private Double otherOutlayPrice;
    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public Double getTravelDietAmount() {
        return travelDietAmount;
    }

    public void setTravelDietAmount(Double travelDietAmount) {
        this.travelDietAmount = travelDietAmount;
    }

    public LocalDateTime getDateTimeStop() {
        return dateTimeStop;
    }

    public void setDateTimeStop(LocalDateTime dateTimeStop) {
        this.dateTimeStop = dateTimeStop;
    }

    public Integer getBreakfastNumber() {
        return breakfastNumber;
    }

    public void setBreakfastNumber(Integer breakfastNumber) {
        this.breakfastNumber = breakfastNumber;
    }

    public Integer getDinnerNumber() {
        return dinnerNumber;
    }

    public void setDinnerNumber(Integer dinnerNumber) {
        this.dinnerNumber = dinnerNumber;
    }

    public Integer getSupperNumber() {
        return supperNumber;
    }

    public void setSupperNumber(Integer supperNumber) {
        this.supperNumber = supperNumber;
    }

    public Transport getTransportType() {
        return transportType;
    }

    public void setTransportType(Transport transportType) {
        this.transportType = transportType;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getAutoCapacity() {
        return autoCapacity;
    }

    public void setAutoCapacity(Integer autoCapacity) {
        this.autoCapacity = autoCapacity;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Double getAccommodationPrice() {
        return accommodationPrice;
    }

    public void setAccommodationPrice(Double accommodationPrice) {
        this.accommodationPrice = accommodationPrice;
    }


    public Double getOtherTicketsPrice() {
        return otherTicketsPrice;
    }

    public void setOtherTicketsPrice(Double otherTicketsPrice) {
        this.otherTicketsPrice = otherTicketsPrice;
    }

    public String getOtherOutlayDesc() {
        return otherOutlayDesc;
    }

    public void setOtherOutlayDesc(String otherOutlayDesc) {
        this.otherOutlayDesc = otherOutlayDesc;
    }

    public Double getOtherOutlayPrice() {
        return otherOutlayPrice;
    }

    public void setOtherOutlayPrice(Double otherOutlayPrice) {
        this.otherOutlayPrice = otherOutlayPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static final class DelegationBuilder {
        private Long id;
        private String description;
        private LocalDateTime dateTimeStart;
        private LocalDateTime dateTimeStop;
        private Double travelDietAmount = 30D;
        private Integer breakfastNumber = 0;
        private Integer dinnerNumber = 0;
        private Integer supperNumber =0;
        private Transport transportType;
        private Double ticketPrice;
        private Integer autoCapacity;
        private Double km;
        private Double accommodationPrice;
        private Double otherTicketsPrice;
        private String otherOutlayDesc;
        private Double otherOutlayPrice;
        private User user;

        private DelegationBuilder() {
        }

        public static DelegationBuilder aDelegation() {
            return new DelegationBuilder();
        }

        public DelegationBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public DelegationBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public DelegationBuilder withDateTimeStart(LocalDateTime dateTimeStart) {
            this.dateTimeStart = dateTimeStart;
            return this;
        }

        public DelegationBuilder withDateTimeStop(LocalDateTime dateTimeStop) {
            this.dateTimeStop = dateTimeStop;
            return this;
        }

        public DelegationBuilder withTravelDietAmount(Double travelDietAmount) {
            this.travelDietAmount = travelDietAmount;
            return this;
        }

        public DelegationBuilder withBreakfastNumber(Integer breakfastNumber) {
            this.breakfastNumber = breakfastNumber;
            return this;
        }

        public DelegationBuilder withDinnerNumber(Integer dinnerNumber) {
            this.dinnerNumber = dinnerNumber;
            return this;
        }

        public DelegationBuilder withSupperNumber(Integer supperNumber) {
            this.supperNumber = supperNumber;
            return this;
        }

        public DelegationBuilder withTransportType(Transport transportType) {
            this.transportType = transportType;
            return this;
        }

        public DelegationBuilder withTicketPrice(Double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public DelegationBuilder withAutoCapacity(Integer autoCapacity) {
            this.autoCapacity = autoCapacity;
            return this;
        }

        public DelegationBuilder withKm(Double km) {
            this.km = km;
            return this;
        }

        public DelegationBuilder withAccommodationPrice(Double accommodationPrice) {
            this.accommodationPrice = accommodationPrice;
            return this;
        }

        public DelegationBuilder withOtherTicketsPrice(Double otherTicketsPrice) {
            this.otherTicketsPrice = otherTicketsPrice;
            return this;
        }

        public DelegationBuilder withOtherOutlayDesc(String otherOutlayDesc) {
            this.otherOutlayDesc = otherOutlayDesc;
            return this;
        }

        public DelegationBuilder withOtherOutlayPrice(Double otherOutlayPrice) {
            this.otherOutlayPrice = otherOutlayPrice;
            return this;
        }

        public DelegationBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public Delegation build() {
            Delegation delegation = new Delegation();
            delegation.setId(id);
            delegation.setDescription(description);
            delegation.setDateTimeStart(dateTimeStart);
            delegation.setDateTimeStop(dateTimeStop);
            delegation.setTravelDietAmount(travelDietAmount);
            delegation.setBreakfastNumber(breakfastNumber);
            delegation.setDinnerNumber(dinnerNumber);
            delegation.setSupperNumber(supperNumber);
            delegation.setTransportType(transportType);
            delegation.setTicketPrice(ticketPrice);
            delegation.setAutoCapacity(autoCapacity);
            delegation.setKm(km);
            delegation.setAccommodationPrice(accommodationPrice);
            delegation.setOtherTicketsPrice(otherTicketsPrice);
            delegation.setOtherOutlayDesc(otherOutlayDesc);
            delegation.setOtherOutlayPrice(otherOutlayPrice);
            delegation.setUser(user);
            return delegation;
        }
    }
}

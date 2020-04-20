package com.example.elwart.user.dto;

import com.example.elwart.user.transport.Transport;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.swing.text.DateFormatter;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DelegationDto {
    private String description;
    private String dateStart;
    private String dateStop;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeStop;
    private Double travelDietAmount = 30D;
    private Integer breakfastNumber = 0;
    private Integer dinnerNumber = 0;
    private Integer supperNumber = 0;
    private String tType;
    private Transport transportType;
    private Double ticketPrice;
    private Integer autoCapacity;
    private Double km;
    private Double accommodationPrice;
    private Double otherTicketsPrice;
    private String otherOutlayDesc;
    private Double otherOutlayPrice;

    public DelegationDto(String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeStop, Double travelDietAmount, Integer breakfastNumber, Integer dinnerNumber, Integer supperNumber, Transport transportType, Double ticketPrice, Integer autoCapacity, Double km, Double accommodationPrice, Double otherTicketsPrice, String otherOutlayDesc, Double otherOutlayPrice) {
        this.description = description;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeStop = dateTimeStop;
        this.travelDietAmount = travelDietAmount;
        this.breakfastNumber = breakfastNumber;
        this.dinnerNumber = dinnerNumber;
        this.supperNumber = supperNumber;
        this.transportType = transportType;
        this.ticketPrice = ticketPrice;
        this.autoCapacity = autoCapacity;
        this.km = km;
        this.accommodationPrice = accommodationPrice;
        this.otherTicketsPrice = otherTicketsPrice;
        this.otherOutlayDesc = otherOutlayDesc;
        this.otherOutlayPrice = otherOutlayPrice;
    }

    public DelegationDto() {
    }

    public void setTimes() {
        dateStart = dateStart.replaceAll("T"," ");
        dateStop = dateStop.replaceAll("T"," ");
        this.dateTimeStart = LocalDateTime.parse(dateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.dateTimeStop = LocalDateTime.parse(dateStop, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if (this.tType.equals("Pociag"))
            this.transportType = Transport.POCIAG;
        else if (this.tType.equals("Auto"))
            this.transportType = Transport.AUTO;
        else
            this.transportType = Transport.AUTOBUS;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
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

    public Double getTravelDietAmount() {
        return travelDietAmount;
    }

    public void setTravelDietAmount(Double travelDietAmount) {
        this.travelDietAmount = travelDietAmount;
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

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateStop() {
        return dateStop;
    }

    public void setDateStop(String dateStop) {
        this.dateStop = dateStop;
    }
}


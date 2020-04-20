package com.example.elwart.user.service;

import com.example.elwart.user.dto.DelegationDto;
import com.example.elwart.user.exception.*;
import com.example.elwart.user.model.Delegation;
import com.example.elwart.user.model.User;
import com.example.elwart.user.repository.DelegationRepository;
import com.example.elwart.user.repository.UserRepository;
import com.example.elwart.user.transport.Transport;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DelegationServiceImpl implements DelegationService {

    private DelegationRepository delegationRepository;
    private UserRepository userRepository;

    @Autowired
    public DelegationServiceImpl(DelegationRepository delegationRepository, UserRepository userRepository) {
        this.delegationRepository = delegationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addDelegation(DelegationDto delegationDto, Long userId) throws BadAutoCapacityException, NotKmException, NotTicketPriceException{
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        Delegation delegation =buildDelgation(delegationDto, user);
        delegationRepository.save(delegation);
    }

    @Override
    public void changeDelegation(DelegationDto delegationDto, Long delgationId) throws BadAutoCapacityException, NotKmException, NotTicketPriceException {
        Delegation delegation = delegationRepository.findById(delgationId).orElseThrow(()->new DelegationNotFoundException(delgationId));
        User user = delegation.getUser();
        Delegation newDelegation = buildDelgation(delegationDto, user);
        newDelegation.setId(delegation.getId());
        delegationRepository.save(newDelegation);

    }

    private Delegation buildDelgation(DelegationDto delegationDto, User user) throws BadAutoCapacityException, NotKmException, NotTicketPriceException {
        return Delegation.DelegationBuilder.aDelegation().withAccommodationPrice(delegationDto.getAccommodationPrice()).
                withAutoCapacity(getAutoCapacity(delegationDto.getAutoCapacity(),delegationDto.getTransportType())).withBreakfastNumber(delegationDto.getBreakfastNumber()).
                withDateTimeStart(delegationDto.getDateTimeStart()).withDateTimeStop(delegationDto.getDateTimeStop()).
                withDescription(delegationDto.getDescription()).withDinnerNumber(delegationDto.getDinnerNumber()).
                withKm(getKm(delegationDto.getKm(),delegationDto.getTransportType())).withOtherOutlayDesc(delegationDto.getOtherOutlayDesc()).
                withOtherOutlayPrice(delegationDto.getOtherOutlayPrice()).withOtherTicketsPrice(delegationDto.getOtherTicketsPrice()).
                withSupperNumber(delegationDto.getSupperNumber()).withTicketPrice(getTicketPrice(delegationDto.getTicketPrice(),delegationDto.getTransportType())).
                withTransportType(delegationDto.getTransportType()).withTravelDietAmount(delegationDto.getTravelDietAmount()).withUser(user).build();
    }

    @Override
    public List<Delegation> getAllDelegations() {
        return (List<Delegation>) delegationRepository.findAll();
    }

    @Override
    public List<Delegation> getAllByDateStartDesc() {
        return delegationRepository.findAllByOrderByDateTimeStartDesc();
    }

    @Override
    public List<Delegation> getAllByUserAndDateStartDesc(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        return delegationRepository.findDelegationsByUserOrderByDateTimeStartDesc(user);
    }

    @Override
    public boolean removeDelegation(Long delegationId, Long userId) {
        Delegation delegation = delegationRepository.findById(delegationId).orElse(null);
        if (delegation==null)
            return false;
        if (delegation.getUser().getId().equals(userId)){
            delegationRepository.delete(delegation);
            return true;
        }
        return false;
    }

    private Double getTicketPrice(Double ticketPrice, Transport transport) throws NotTicketPriceException {
        if (ticketPrice == null && transport.isPublicTransport())
            throw new NotTicketPriceException();
        return ticketPrice;
    }
    private Integer getAutoCapacity(Integer autoCapacity, Transport transport) throws BadAutoCapacityException {
        if (autoCapacity == null && transport.isPublicTransport())
            return  autoCapacity;
        if (autoCapacity == null)
            throw new BadAutoCapacityException(autoCapacity);
        if (autoCapacity >= 1900 || autoCapacity < 900)
            throw new BadAutoCapacityException(autoCapacity);
        return autoCapacity;
    }
    private Double getKm(Double km, Transport transport) throws NotKmException{
        if (km == null && !transport.isPublicTransport())
            throw new NotKmException();
        return km;
    }
}

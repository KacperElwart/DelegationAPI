package com.example.elwart.user.service;

import com.example.elwart.user.dto.DelegationDto;
import com.example.elwart.user.exception.BadAutoCapacityException;
import com.example.elwart.user.exception.NotKmException;
import com.example.elwart.user.exception.NotTicketPriceException;
import com.example.elwart.user.model.Delegation;

import java.time.LocalDateTime;
import java.util.List;

public interface DelegationService {

    void addDelegation(DelegationDto delegationDto, Long userId) throws BadAutoCapacityException, NotKmException, NotTicketPriceException;

    void changeDelegation(DelegationDto delegationDto, Long delgationId) throws BadAutoCapacityException, NotKmException, NotTicketPriceException;

    List<Delegation> getAllDelegations();

    List<Delegation> getAllByDateStartDesc();

    List<Delegation> getAllByUserAndDateStartDesc(Long userId);

    boolean removeDelegation(Long delegationId, Long userId);
}

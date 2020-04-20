package com.example.elwart.user.repository;

import com.example.elwart.user.model.Delegation;
import com.example.elwart.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DelegationRepository  extends CrudRepository<Delegation,Long> {
    List<Delegation> findAllByOrderByDateTimeStartDesc();
    List<Delegation> findDelegationsByUserOrderByDateTimeStartDesc(User user);
}

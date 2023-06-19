package com.pragma.smallsquare.insfrastructure.output.jpa.repository;

import com.pragma.smallsquare.insfrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("SELECT ord FROM OrderEntity ord WHERE ord.idCustomer = ?1 ORDER BY ord.orderDate DESC")
    List<OrderEntity> findLastOrderByCustomerId(Integer customerId, Pageable pageable);

    List<OrderEntity> findAllByStatus(String status, Pageable pageable);
}

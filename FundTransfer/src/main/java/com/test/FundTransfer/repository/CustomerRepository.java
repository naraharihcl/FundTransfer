package com.test.FundTransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.FundTransfer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	

}

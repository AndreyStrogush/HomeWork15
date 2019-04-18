package com.homeworks.dao;

import com.homeworks.entity.Customer;

public interface CustomerDao {
    boolean insertCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    Customer readCustomer(Long id);

    boolean deleteCustomer(Long id);
}

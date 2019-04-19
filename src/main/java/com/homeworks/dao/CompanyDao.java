package com.homeworks.dao;

import com.homeworks.entity.Company;

public interface CompanyDao {
    boolean insertCompany(Company company);

    boolean updateCompany(Company company);

    Company findByld(Long id);

    boolean deleteCompany(Long id);
}

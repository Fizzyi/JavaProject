package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/9
 */
public interface AddressBookService {
    void add(AddressBook addressBook);

    List<AddressBook> list();

    AddressBook defaultAddress();

    void setDefaultAddress(AddressBook addressBook);

    AddressBook get(Long id);

    void update(AddressBook addressBook);

    void delete(Long id);
}

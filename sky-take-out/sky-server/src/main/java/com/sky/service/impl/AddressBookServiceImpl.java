package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/9
 */

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    public AddressBookMapper addressBookMapper;

    /**
     * 新增地址
     *
     * @param addressBook
     */
    @Override
    public void add(AddressBook addressBook) {
        Long id = BaseContext.getCurrentId();
        addressBook.setUserId(id);
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    /**
     * 查询该用户的所有地址信息
     *
     * @return
     */
    @Override
    public List<AddressBook> list() {
        Long id = BaseContext.getCurrentId();
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(id);
        return addressBookMapper.list(addressBook);
    }

    /**
     * 查询用户默认地址
     *
     * @return
     */
    @Override
    public AddressBook defaultAddress() {
        Long id = BaseContext.getCurrentId();
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(id);
        addressBook.setIsDefault(1);
        List<AddressBook> list = addressBookMapper.list(addressBook);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 设置默认地址
     *
     * @param addressBook
     */
    @Override
    public void setDefaultAddress(AddressBook addressBook) {
        // 先将其他地址都修改为非默认地址，在将传入的地址修改为默认地址
        Long id = BaseContext.getCurrentId();

        AddressBook addressBook1 = new AddressBook();
        addressBook1.setUserId(id);
        addressBook1.setIsDefault(0);
        addressBookMapper.update(addressBook1);

        addressBook.setIsDefault(1);
        addressBook1.setUserId(id);
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据id 查询地址
     *
     * @param id
     * @return
     */
    @Override
    public AddressBook get(Long id) {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(id);
        List<AddressBook> list = addressBookMapper.list(addressBook);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据id 修改地址
     *
     * @param addressBook
     */
    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据id 删除地址
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        addressBookMapper.delete(id);
    }


}

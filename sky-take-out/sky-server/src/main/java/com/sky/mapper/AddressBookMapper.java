package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/9
 */
@Mapper
public interface AddressBookMapper {

    void insert(AddressBook addressBook);

    List<AddressBook> list(AddressBook addressBook);

    void update(AddressBook addressBook);

    @Delete("delete from address_book where id =#{id};")
    void delete(Long id);
}

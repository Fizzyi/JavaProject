package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo class
 *
 * @author Zhaohangyi
 * @time 2023/11/9
 */

@RestController
@RequestMapping("/user/addressBook")
@Api("地址管理相关接口")
@Slf4j
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping()
    @ApiOperation("新增地址")
    public Result add(@RequestBody AddressBook addressBook) {
        log.info("新增地址，参数为:{}", addressBook);
        addressBookService.add(addressBook);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("查询当前登录用户的所有地址信息")
    public Result<List<AddressBook>> list() {
        List<AddressBook> addressBookList = addressBookService.list();
        return Result.success(addressBookList);
    }

    @GetMapping("/default")
    @ApiOperation("查询默认地址")
    public Result<AddressBook> defaultAddress() {
        AddressBook addressBook = addressBookService.defaultAddress();
        return Result.success(addressBook);
    }

    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefaultAddress(@RequestBody AddressBook addressBook) {
        addressBookService.setDefaultAddress(addressBook);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址")
    public Result<AddressBook> getAddressById(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.get(id);
        return Result.success(addressBook);
    }

    @PutMapping("")
    @ApiOperation("根据id 修改地址")
    public Result updateAddressById(@RequestBody AddressBook addressBook) {
        addressBookService.update(addressBook);
        return Result.success();
    }


    @DeleteMapping()
    @ApiOperation("根据id删除数据")
    public Result deleteById(@RequestParam Long id) {
        addressBookService.delete(id);
        return Result.success();

    }
}

package com.agiview.transaction.test;

import com.agiview.transaction.entity.UserAccount;
import com.agiview.transaction.service.UserAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    UserAccountService userAccountService;

    @Test
    public void saveTest() {

        UserAccount userAccount1 = new UserAccount();

        userAccount1.setName("张三");

        userAccount1.setBalance(new BigDecimal("1000"));

        userAccountService.save(userAccount1);

        UserAccount userAccount2 = new UserAccount();

        userAccount2.setName("李四");

        userAccount2.setBalance(new BigDecimal("1000"));

        userAccountService.save(userAccount2);
    }

    @Test
    public void transferMoneyTest() {

        userAccountService.transferMoney("张三", "李四", new BigDecimal("100"));

    }
}

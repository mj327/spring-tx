package com.agiview.transaction.service;

import com.agiview.transaction.dao.UserAccountDao;
import com.agiview.transaction.entity.UserAccount;
import com.agiview.transaction.manager.TxManager;
import com.agiview.transaction.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserAccountService {

    @Autowired
    TxManager txManager;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserAccountDao userAccountDao;


    public void save(UserAccount userAccount) {

        userAccountRepository.save(userAccount);

    }

    public void transferMoney(String sourceName, String targetName, BigDecimal money) {

        try {

            txManager.open();

            UserAccount sourceUserAccount = userAccountDao.findByName(sourceName);

            BigDecimal sourceBalance = sourceUserAccount.getBalance().subtract(money);

            sourceUserAccount.setBalance(sourceBalance);

            userAccountDao.update(sourceUserAccount);

            int i = 1/0;

            UserAccount targetUserAccount = userAccountDao.findByName(targetName);

            BigDecimal targetBalance = targetUserAccount.getBalance().add(money);

            targetUserAccount.setBalance(targetBalance);

            userAccountDao.update(targetUserAccount);

            txManager.commit();

        } catch (Exception e) {

            e.printStackTrace();

            txManager.rollback();

        } finally {

            txManager.close();

        }
    }

}

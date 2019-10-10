package com.agiview.transaction.manager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Aspect
public class TxManager {

    @Autowired
    ConnectionManager connectionManager;

    @Pointcut("execution(public * com.agiview.transaction.service.UserAccountService.transferMoney(..))")
    private void tx() {}


    public void open() {
        try {
            connectionManager.getCurrentConnection().setAutoCommit(false);
            System.out.println("前置执行了。。。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connectionManager.getCurrentConnection().commit();
            System.out.println("后置执行了。。。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connectionManager.getCurrentConnection().rollback();
            System.out.println("异常执行了。。。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connectionManager.getCurrentConnection().close();
            connectionManager.removeCurrentConnection();
            System.out.println("释放连接。。。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("tx()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            //1.获取参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.open();
            //3.执行方法
            rtValue = pjp.proceed(args);
            //4.提交事务
            this.commit();

            //返回结果
            return  rtValue;

        }catch (Throwable e){
            //5.回滚事务
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            this.close();
        }
    }

}

package com.oocl.jyhon.serviceimpl;

import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.dao.UserEntityDao;
import com.oocl.jyhon.daoimple.UserEntityDaoImple;
import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public class UserEntityServiceImpl implements UserEntityService {
    //    UserEntityDao userEntityDaoImple = new UserEntityDaoImple();

    static UserEntityDaoImple userEntityDao;

    public UserEntityDao getUserEntityDao() {
        return userEntityDao;
    }

    public void setUserEntityDao(UserEntityDaoImple userEntityDao) {
        this.userEntityDao = userEntityDao;
    }

    public int addEntity(UserEntity userEntity) {
        userEntity.setRole("customer");
        userEntity.setStatusId(2);
        return userEntityDao.addEntity(userEntity);
    }

    public int updateEntity(UserEntity userEntity) {
        return userEntityDao.updateEntity(userEntity);
    }

    public UserEntity verify(UserEntity userEntity) {

        return (UserEntity) userEntityDao.verify(userEntity);
    }
}

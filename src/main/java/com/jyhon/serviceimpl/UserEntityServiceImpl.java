package com.jyhon.serviceimpl;

import com.jyhon.dao.UserEntityDao;
import com.jyhon.entiy.UserEntity;
import com.jyhon.service.UserEntityService;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */

public class UserEntityServiceImpl implements UserEntityService {
    //    UserEntityDao userEntityDaoImple = new UserEntityDaoImple();

    private UserEntityDao userEntityDao;

    public UserEntityDao getUserEntityDao() {
        return userEntityDao;
    }

    public void setUserEntityDao(UserEntityDao userEntityDao) {
        this.userEntityDao = userEntityDao;
        System.out.println(this.userEntityDao);
    }

    public int addEntity(UserEntity userEntity) {
        userEntity.setRole("customer");
        userEntity.setStatusId(2);
        return this.userEntityDao.addEntity(userEntity);
    }

    public int updateEntity(UserEntity userEntity) {
        return this.userEntityDao.updateEntity(userEntity);
    }

    public UserEntity verify(UserEntity userEntity) {

        return (UserEntity) this.userEntityDao.verify(userEntity);
    }
}

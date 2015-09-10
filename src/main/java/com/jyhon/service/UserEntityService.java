package com.jyhon.service;

import com.jyhon.entiy.UserEntity;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public interface UserEntityService {
    public int addEntity(UserEntity Entity);

    public int updateEntity(UserEntity entity);

    public UserEntity verify(UserEntity userEntity);
}

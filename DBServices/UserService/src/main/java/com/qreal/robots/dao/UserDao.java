package com.qreal.robots.dao;

import com.qreal.robots.thrift.gen.TUser;

/**
 * DAO for userDB.
 */
public interface UserDao {

    /**
     * Saves user.
     *
     * @param user user to save (Id must not be set)
     */
    void save(TUser user);

    /**
     * Finds user by UserName. (Or null)
     *
     * @param username name of user to find
     */
    TUser findByUserName(String username);

    /**
     * Updates user state
     *
     * @param  user user to update (Id must be set)
     */

    void update(TUser user);


    /**
     * Test if user with specified name exists.
     *
     * @param username name of user to test if exists
     * @return [description]
     */
    boolean isUserExist(String username);
}

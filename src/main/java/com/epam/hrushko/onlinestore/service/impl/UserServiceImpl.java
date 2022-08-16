package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.RoleDao;
import com.epam.hrushko.onlinestore.dao.UserDao;
import com.epam.hrushko.onlinestore.dao.UserInfoDao;
import com.epam.hrushko.onlinestore.dao.impl.RoleDaoImpl;
import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserService;
import com.epam.hrushko.onlinestore.service.validate.EmailValidator;
import com.epam.hrushko.onlinestore.service.validate.NameValidator;
import com.epam.hrushko.onlinestore.service.validate.PhoneValidator;
import com.epam.hrushko.onlinestore.service.validate.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * User service class
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Logining
     * @param email
     * @param password
     * @return successfully log in or not
     * @throws ServiceException
     */
    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        if (email == null || password == null) {
            return Optional.empty();
        }
        if (!isEmailValid(email)) {
            return Optional.empty();
        }

        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            return userDao.findByEmailAndPassword(email, password);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot find user");
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Registration
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param password
     * @return successfully or not
     * @throws ServiceException
     */
    @Override
    public boolean register(String name, String surname, String email, String phone, String password) throws ServiceException {
        if (name == null || surname == null || email == null ||
        phone == null || password == null) {
            return false;
        }
        if (!isEmailValid(email) || !isInfoValid(name, surname, phone)) {
            return false;
        }
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            if (userDao.findByEmail(email).isPresent()) {
                return false;
            }
            RoleDao roleDao = DaoFactory.getInstance().getRoleDao();
            Optional<Role> role = roleDao.read(1);
            if (!role.isPresent()) {
                return false;
            }
            long phon = Long.parseLong(phone);
            UserInfoDao userInfoDao = DaoFactory.getInstance().getUserInfoDao();
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setSurname(surname);
            userInfo.setPhone(phon);
            int userInfoId = userInfoDao.create(userInfo);
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setUserInfoId(userInfoId);
            user.setRoleId(role.get().getId());
            userDao.create(user);
            return true;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findUserById(int userId) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            Optional<User> ans;
            ans = userDao.findById(userId);
            return ans;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getUser(List<Order> orders) throws ServiceException {
        List<User> users = new LinkedList<>();
        try {
            for (Order order:
                 orders) {
                Optional<User> user = findUserById(order.getUserId());
                if (user.isPresent()) {
                    if (!users.contains(user.get())) {
                        users.add(user.get());
                    }
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
        return users;
    }

    private boolean isEmailValid(String email) {
        Validator validator = new EmailValidator();
        return validator.isValid(email);
    }

    private boolean isInfoValid(String name, String surname, String phone) {
        Validator nameValid = new NameValidator();
        Validator phoneValid = new PhoneValidator();
        return nameValid.isValid(name) && nameValid.isValid(surname) && phoneValid.isValid(phone);
    }

}

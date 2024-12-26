package vn.com.anhTuan.query_service.service;


import vn.com.anhTuan.commons.cqrs.aggregate.UserAggregate;

public interface UserService {

    void createUser(UserAggregate aggregate);

    void updateUser(UserAggregate aggregate);

}

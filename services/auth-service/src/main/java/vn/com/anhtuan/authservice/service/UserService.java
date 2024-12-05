package vn.com.anhtuan.authservice.service;

import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhtuan.authservice.dto.request.SignUpRequest;
import vn.com.anhtuan.authservice.dto.response.UserResponse;

import java.math.BigDecimal;

public interface UserService {

    RestResponse<Void> createUser(SignUpRequest request);

    RestResponse<UserResponse> getOneUser(Long id, boolean failure, int delay);

    void debitBalance(Long id, BigDecimal amount);

}

package jelian.code.springdata.service;

import jelian.code.springdata.domain.User;
import jelian.code.springdata.dto.LoginDto;
import jelian.code.springdata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public Boolean validateLogin(LoginDto loginDto) {
        return validateUserNameAndPassword(loginDto.getUsername(), loginDto.getPassword());
    }


    private Boolean validateUserNameAndPassword(String username, String password) {
        Boolean response = Boolean.FALSE;
        if (Objects.nonNull(username) && Objects.nonNull(password)) {
            try {
                /*insert checkIn storage procedure here*/
                User user = userRepository.findByUsernameAndUserPasswordAndUserEnable(username, password, true);
                log.info("success!!!");

                response = Objects.nonNull(user.getIdUser());
            } catch (Exception e) {
                log.info("fail!!!");
            }
        }

        return response;
    }
}
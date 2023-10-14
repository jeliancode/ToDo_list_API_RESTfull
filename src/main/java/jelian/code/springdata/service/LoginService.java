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
        return validateUserNameAndPassword(loginDto);
    }


    private Boolean validateUserNameAndPassword(LoginDto loginDto) {
        Boolean response = Boolean.FALSE;
        if (Objects.nonNull(loginDto.getUsername()) && Objects.nonNull(loginDto.getPassword())) {
            try {
                User user = userRepository.findByUsernameAndUserPasswordAndUserEnable(loginDto.getUsername(), loginDto.getPassword(), true);
                if (loginDto.getRetryTimes() > 3) {
                    user.setUserEnable(Boolean.FALSE);
                    userRepository.save(user);
                    response = Boolean.FALSE;
                } else {
                    response = Objects.nonNull(user.getIdUser());
                }
            } catch (Exception e) {
                log.info("fail!!!");
            }
        }

        return response;
    }

}

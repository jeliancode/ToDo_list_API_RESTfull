package jelian.code.springdata.controller;

import jakarta.validation.Valid;
import jelian.code.springdata.dto.LoginDto;
import jelian.code.springdata.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public Boolean login(@RequestBody @Valid LoginDto loginDto) {
        return loginService.validateLogin(loginDto);
    }

}
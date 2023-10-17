package jelian.code.springdata.controller;

import jakarta.validation.Valid;
import jelian.code.springdata.dto.LoginDto;
import jelian.code.springdata.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @PostMapping
    public Boolean login(@RequestBody @Valid LoginDto loginDto) {
        return loginService.validateLogin(loginDto);
    }
}
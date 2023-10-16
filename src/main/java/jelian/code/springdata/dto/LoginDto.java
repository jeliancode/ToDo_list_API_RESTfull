package jelian.code.springdata.dto;

import lombok.Getter;

import lombok.Getter;

@Getter
public class LoginDto {

    private String username;
    private String password;
    private Integer retryTimes;

}

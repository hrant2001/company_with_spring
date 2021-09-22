package com.hrant.dto;

public class AuthenticationResponse {

    private String username;
    private String token;
    private EmployeeDto employeeDto;

    public AuthenticationResponse(String username, String token, EmployeeDto employeeDto) {
        this.username = username;
        this.token = token;
        this.employeeDto = employeeDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}

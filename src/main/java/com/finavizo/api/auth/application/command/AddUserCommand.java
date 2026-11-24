package com.finavizo.api.auth.application.command;

public class AddUserCommand {
    public String name;
    public String username;
    public String password;

    public AddUserCommand(
        String name,
        String username,
        String password
    ) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
package models.DTOS;

import models.UserRole;

public record RegisterDTO(String login, String password, UserRole userRole){

}


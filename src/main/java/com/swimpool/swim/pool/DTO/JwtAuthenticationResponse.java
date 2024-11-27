package com.swimpool.swim.pool.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с токеном доступа")
public class JwtAuthenticationResponse {
    @Schema(description = "Access token", example = "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pb")
    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public JwtAuthenticationResponse() {
    }

    public String getToken(){
        return token;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponse [token=" + token + "]";
    }
}

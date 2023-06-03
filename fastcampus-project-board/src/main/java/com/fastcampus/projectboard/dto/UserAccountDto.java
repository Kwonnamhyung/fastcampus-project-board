package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.common.domain.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAccountDto {

    private final String userAccountId;
    private final String userAccountPassword;
    private final String email;
    private final String nickname;
    private final String memo;

    private final String createdBy;
    private final LocalDateTime createdAt;
    private final String modifiedBy;
    private final LocalDateTime modifiedAt;

    public UserAccountDto(String userAccountId, String userAccountPassword, String email, String nickname, String memo, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
        this.userAccountId = userAccountId;
        this.userAccountPassword = userAccountPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedBy = modifiedBy;
        this.modifiedAt = modifiedAt;
    }
}

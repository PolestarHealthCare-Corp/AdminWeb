package com.refsys.adminweb.dto.response;

import com.refsys.adminweb.domain.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

	private String memberPhone; //01012345678
	private String memberUsername;
	private Integer memberRole; //ADMIN(0), USER(1)
	private Boolean memberIsActive; //FALSE(0), TRUE(1)
	private LocalDateTime memberCreatedAt;

	public static MemberResponse from(Member member) {
		return new MemberResponse(member.getMemberPhone(), member.getMemberUsername(), member.getMemberRole(), member.getMemberIsActive(), member.getMemberCreatedAt());
	}

}

package com.refsys.adminweb.controller.api;

import com.refsys.adminweb.dto.Response;
import com.refsys.adminweb.dto.request.MemberJoinRequest;
import com.refsys.adminweb.dto.request.MemberSearchCond;
import com.refsys.adminweb.dto.request.MemberUpdateRequest;
import com.refsys.adminweb.dto.response.MemberJoinResponse;
import com.refsys.adminweb.dto.response.MemberResponse;
import com.refsys.adminweb.dto.response.MemberUpdateResponse;
import com.refsys.adminweb.service.MemberService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@Slf4j
public class MemberRestController {

	private final MemberService memberService;

	//TODO 검증 추가 예정

	//회원가입, 회원추가
	@PostMapping("/join")
	public ResponseEntity<Response<MemberJoinResponse>> joinMember(@RequestBody MemberJoinRequest request) {
		MemberJoinResponse result = memberService.joinMember(request);
		log.info("회원번호 {}이 가입되었습니다.", request.getMemberPhone());
		return ResponseEntity.created(URI.create("/api/v1/members/join"+result.getMemberPhone()))
						.body(Response.success(result));
	}

	//회원상세조회
	@GetMapping("/{phone}")
	public ResponseEntity<Response<MemberResponse>> findByMemberPhone(@PathVariable("phone") String phone) {
		MemberResponse result = memberService.findByMemberPhone(phone);
		return ResponseEntity.ok().body(Response.success(result));
	}

	//회원동적검색
	@GetMapping
	public ResponseEntity<Response<List<MemberResponse>>> findAll(@RequestBody MemberSearchCond condition) {
		List<MemberResponse> result = memberService.findAll(condition);
		return ResponseEntity.ok().body(Response.success(result));
	}

	//회원수정 -> 관리자만 가능
	@PutMapping("/{phone}")
	public ResponseEntity<Response<MemberUpdateResponse>> updateMember(@PathVariable("phone") String phone, @RequestBody MemberUpdateRequest request) {
		MemberUpdateResponse result = memberService.updateMember(phone, request);
		log.info("회원번호 {}이 수정되었습니다.", phone);
		return ResponseEntity.ok().body(Response.success(result));
	}

}

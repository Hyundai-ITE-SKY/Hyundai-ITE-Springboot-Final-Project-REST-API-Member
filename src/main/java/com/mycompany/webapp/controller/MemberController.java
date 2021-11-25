package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Cart;
import com.mycompany.webapp.dto.Coupon;
import com.mycompany.webapp.dto.Member;
import com.mycompany.webapp.dto.QnA;
import com.mycompany.webapp.dto.WishList;
import com.mycompany.webapp.security.JwtUtil;
import com.mycompany.webapp.service.MemberService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

	@Resource
	private MemberService memberService;

//	@Resource
//	private AuthenticationManager authenticationManager;

//	@RequestMapping("sendAuth")
//	public Map<String, String> sendAuth(@RequestHeader("Authorization") String authorization) {
//	   log.info("실행");
//	   log.info(authorization);
//	   String jwt = authorization.substring(7);
//	   Map<String, String> map = new HashMap<>();
//	   map.put("jwt", jwt);
//	   return map;
//	}
//	
	// 회원정보 보기
	@GetMapping("/info")
	public Member getInfo(HttpServletRequest request) {
		log.info("실행");

//		String jwt = request.getHeader("Authorization").substring(7);
//		Claims claims = JwtUtil.validateToken(jwt);
//		String mid = JwtUtil.getMid(claims);
		
		String mid = request.getAttribute("mid").toString();
		Member member = memberService.selectInfo(mid);

		return member;
	}

	// mid로 위시리스트 받아오기
	@GetMapping("/wishlist")
	public List<WishList> getWishList(@RequestParam String mid) {
		List<WishList> wishList = memberService.selectWishlist(mid);
		return wishList;
	}

	// mid로 보유중인 쿠폰 받아오기
	@GetMapping("/coupon")
	public List<Coupon> getCouponList(@RequestParam String mid) {
		List<Coupon> couponList = memberService.selectCouponlist(mid);
		return couponList;
	}

	// mid로 qna목록 받아오기
	@GetMapping("/qna")
	public List<QnA> getqnaList(@RequestParam String mid) {
		List<QnA> qnaList = memberService.selectQnaList(mid);
		log.info(mid);
		log.info(qnaList.toString());
		return qnaList;
	}

	// 장바구니 불러오기
	@GetMapping("/mycart")
	public List<Cart> getMycart(@RequestParam String mid) {
		List<Cart> mycart = memberService.selectMycart(mid);
		return mycart;
	}
}

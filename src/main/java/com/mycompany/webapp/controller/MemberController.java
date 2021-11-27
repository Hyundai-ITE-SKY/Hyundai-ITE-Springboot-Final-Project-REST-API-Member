package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<WishList> getWishList(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<WishList> wishList = memberService.selectWishlist(mid);
		return wishList;
	}

	// mid로 보유중인 쿠폰 받아오기
	@GetMapping("/coupon")
	public List<Coupon> getCouponList(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<Coupon> couponList = memberService.selectCouponlist(mid);
		return couponList;
	}

	// mid로 qna목록 받아오기
	@GetMapping("/qna")
	public List<QnA> getqnaList(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<QnA> qnaList = memberService.selectQnaList(mid);
		log.info(mid);
		log.info(qnaList.toString());
		return qnaList;
	}

	// 장바구니 불러오기
	@GetMapping("/mycart")
	public List<Cart> getMycart(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<Cart> mycart = memberService.selectMycart(mid);
		return mycart;
	}
	
	//mid, pid로 wishlist 추가
	@PostMapping("/createwishlist")
	public int createWishList(HttpServletRequest request, String pid) {
		String mid = request.getAttribute("mid").toString();
		WishList wishList = new WishList();
		wishList.setMid(mid);
		wishList.setPid(pid);
		log.info(mid);
		log.info(pid);
		return memberService.createWishList(wishList);
	}
	
	//mid, pid로 wishlist 삭제
	@DeleteMapping("/deletewishlist")
	public void deleteWishList(HttpServletRequest request, String pid) {
		String mid = request.getAttribute("mid").toString();
		WishList wishList = new WishList();
		wishList.setMid(mid);
		wishList.setPid(pid);
		log.info(mid);
		log.info(pid);
		memberService.deleteWishList(wishList);
	}
	
}

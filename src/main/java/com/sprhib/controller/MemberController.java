package com.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Member;
import com.sprhib.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addMemberPage() {
		ModelAndView modelAndView = new ModelAndView("add-member-form");
		modelAndView.addObject("member", new Member());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingMember(@ModelAttribute Member member) {

		memberService.addMember(member);
		
		String message = "Member was successfully added.";

		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfMembers() {
		ModelAndView modelAndView = new ModelAndView("list-of-members");
		
		List<Member> members = memberService.getMembers();
		modelAndView.addObject("members", members);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editMemberPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-member-form");
		Member member = memberService.getMember(id);
		modelAndView.addObject("member",member);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingMember(@ModelAttribute Member member, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		memberService.updateMember(member);
		
		String message = "Member was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteMember(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		memberService.deleteMember(id);
		String message = "Member was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}

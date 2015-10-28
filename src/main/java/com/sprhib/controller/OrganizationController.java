package com.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Organization;
import com.sprhib.service.OrganizationService;
import java.util.Locale;
import org.springframework.context.MessageSource;

@Controller
@RequestMapping(value="/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addOrganizationPage() {
		ModelAndView modelAndView = new ModelAndView("add-organization-form");
		modelAndView.addObject("organization", new Organization());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingOrganization(@ModelAttribute Organization organization, Locale loc) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		organizationService.addOrganization(organization);
		
		String message = messageSource.getMessage("controller.organization.added", null, loc);
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfOrganizations() {
		ModelAndView modelAndView = new ModelAndView("list-of-organizations");
		
		List<Organization> organizations = organizationService.getOrganizations();
		modelAndView.addObject("organizations", organizations);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editOrganizationPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-organization-form");
		Organization organization = organizationService.getOrganization(id);
		modelAndView.addObject("organization",organization);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingOrganization(@ModelAttribute Organization organization, @PathVariable Integer id, Locale loc) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		organizationService.updateOrganization(organization);
		
		String message = messageSource.getMessage("controller.organization.edited", null, loc);
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteOrganization(@PathVariable Integer id, Locale loc) {
		ModelAndView modelAndView = new ModelAndView("home");
		organizationService.deleteOrganization(id);
		String message = messageSource.getMessage("controller.organization.deleted", null, loc);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}

package com.sprhib.controller;

import com.sprhib.model.Organization;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Team;
import com.sprhib.service.OrganizationService;
import com.sprhib.service.TeamService;
import java.beans.PropertyEditorSupport;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addTeamPage() {
		ModelAndView modelAndView = new ModelAndView("add-team-form");
		modelAndView.addObject("team", new Team());
		modelAndView.addObject("organizations", organizationService.getOrganizations());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingTeam(@ModelAttribute Team team, Locale loc) {

		// Allow storing team without organization
		Organization o = team.getOrganization();
		if (o != null && o.getId() == null) {
			team.setOrganization(null);
		}

		teamService.addTeam(team);
		
		String message = messageSource.getMessage("controller.team.added", null, loc);

		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfTeams(@RequestParam(required = false) String message) {
		ModelAndView modelAndView = new ModelAndView("list-of-teams");
		
		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-team-form");
		Team team = teamService.getTeam(id);
		modelAndView.addObject("team",team);
		modelAndView.addObject("organizations", organizationService.getOrganizations());
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingTeam(@ModelAttribute Team team, @PathVariable Integer id, Locale loc) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:/team/list");
		
		teamService.updateTeam(team);
		
		String message = messageSource.getMessage("controller.team.edited", null, loc);
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id, Locale loc) {
		ModelAndView modelAndView = new ModelAndView("redirect:/team/list");
		teamService.deleteTeam(id);
		String message = messageSource.getMessage("controller.team.deleted", null, loc);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Organization.class, new OrganizationEditor());
	}

	public class OrganizationEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (text == null || text.isEmpty()) {
				setValue(null);
				return;
			}
			int id = Integer.parseInt(text);
			setValue(organizationService.getOrganization(id));
		}
	}
}

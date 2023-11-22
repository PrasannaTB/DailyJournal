package com.example.DailyJournal.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.DailyJournal.domain.*;

@Controller
public class JournalController {
	@Autowired
	private JournalRepository repository;

	@Autowired
	private MoodRepository mrepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Show all journals
	
	
	@RequestMapping(value = { "/", "/diary" })
	public String DiaryJournal(Model model) {

		model.addAttribute("journals", repository.findAll());

		return "diary";
	}

	// RESTful service to get all journals
	@RequestMapping(value = "/journals", method = RequestMethod.GET)
	public @ResponseBody List<Journal> journalRest() {
		return (List<Journal>) repository.findAll();
	}

	// RESTful service to get journals by id
	@RequestMapping(value = "/journal/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Journal> findJournalRest(@PathVariable("id") Long journalId) {
		return repository.findById(journalId);
	}

	// Delete journals
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteJournal(@PathVariable("id") Long journalId, Model model) {
		repository.deleteById(journalId);
		return "redirect:../diary";

	}

	// Add new journals
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addJournal(Model model) {
		model.addAttribute("journal", new Journal());
		model.addAttribute("moods", mrepository.findAll());
		return "addjournal";
	}

	// Save new journals
	@PostMapping("/save")
	public String save(@ModelAttribute Journal journal) {
		repository.save(journal);
		return "redirect:diary";
	}

	/*
	 * 
	 * @RequestMapping(value = "/save", method = RequestMethod.POST) public String
	 * save(Journal journal) { repository.save(journal); return "redirect:diary"; }
	 */

	@RequestMapping(value = "/edit")
	public String editJournal(Long id, Model model) {
		Journal journal = repository.findById(id).orElse(null);
		if (journal != null) {
			model.addAttribute("journal", journal);
			model.addAttribute("mood", mrepository.findAll());

			return "editjournal";
		} else {
			// Handle the case where the book with the given ID doesn't exist
			return "redirect:/";
		}
	}

	@PostMapping("/edit/{id}")
	public String editJournalSubmit(@PathVariable("id") Long journalId, @ModelAttribute Journal editedJournal) {

		editedJournal.setId(journalId);
		repository.save(editedJournal);
		return "redirect:/diary";
	}

	// Edit journals
	/*
	 * @RequestMapping(value = "/edit/{id}") public String
	 * editJournal(@PathVariable("id") Long journalId, Model model) { Journal
	 * journal = repository.findById(journalId).orElse(null); if (journal != null) {
	 * model.addAttribute("journal", journal); model.addAttribute("mood",
	 * mrepository.findAll());
	 * 
	 * return "editjournal"; } else { // Handle the case where the book with the
	 * given ID doesn't exist return "redirect:/diary"; } }
	 * 
	 * // Save edited journals
	 * 
	 * @PostMapping("/edit/{id}") public String
	 * editJournalSubmit(@PathVariable("id") Long journalId, @ModelAttribute Journal
	 * editedJournal) { editedJournal.setId(journalId);
	 * repository.save(editedJournal); return "redirect:/diary"; }
	 */

}

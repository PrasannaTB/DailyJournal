package com.example.DailyJournal.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DailyJournal.domain.Journal;
import com.example.DailyJournal.domain.JournalRepository;
import com.example.DailyJournal.domain.MoodRepository;



@Controller
public class JournalController {
	@Autowired
	private JournalRepository repository;

	@Autowired
	private MoodRepository mrepository;

	@RequestMapping(value={"/", "/diary"})
	public String DiaryJournal(Model model) {

		model.addAttribute("journals", repository.findAll());

		return "diary";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteJournal(@PathVariable("id") Long journalId, Model model) {
		repository.deleteById(journalId);
		return "redirect:../diary";

	}

	@RequestMapping(value = "/add")
	public String addJournal(Model model) {
		model.addAttribute("journal", new Journal());
		model.addAttribute("moods", mrepository.findAll());
		return "addjournal";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Journal journal) {
		repository.save(journal);
		return "redirect:diary";
	}
	
	@GetMapping("/edit/{id}")
	public String editJournal(@PathVariable("id") Long journalId, Model model) {
	    model.addAttribute("journal", repository.findById(journalId).orElse(new Journal()));
	    model.addAttribute("moods", mrepository.findAll());
	    return "editjournal";
	}

	@PostMapping("/edit/{id}")
	public String editJournalSubmit(@PathVariable("id") Long journalId, @ModelAttribute Journal editedJournal) {
	    editedJournal.setId(journalId);
	    repository.save(editedJournal);
	    return "redirect:/diary";
	}

	
	/*
	@RequestMapping(value = "/edit")
	public String editJournal(Long id, Model model) {
		Journal journal = repository.findById(id).orElse(null);
		if (journal != null) {
			model.addAttribute("journal", journal);
			model.addAttribute("moods", mrepository.findAll());
			
			return "editjournal";
		} else {
			// Handle the case where the book with the given ID doesn't exist
			return "redirect:/diary";
		}
	}

	@PostMapping("/edit/{id}")
	public String editJournalSubmit(@PathVariable("id") Long journalId, @ModelAttribute Journal editedJournal) {

		editedJournal.setId(journalId);
		repository.save(editedJournal);
		return "redirect:/diary";
	}
	
	*/

}

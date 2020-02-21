package com.plebicom.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/batch")
@CrossOrigin
public class BatchController {

	@PostMapping(path="/articleImport")
	public @ResponseBody ResponseEntity triggerArticleImport () {
		return ResponseEntity.ok(null);
	}
}

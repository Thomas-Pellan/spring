package com.plebicom.site.controller;

import com.plebicom.service.ArticleService;
import com.plebicom.service.ConfigurationService;
import com.plebicom.service.ImporterService;
import com.plebicom.site.dto.ApiResponseDTO;
import com.plebicom.site.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/importer")
@CrossOrigin
public class ImporterController {

	@Autowired
	private ImporterService importerService;

	@GetMapping(path="/article")
	public @ResponseBody ResponseEntity triggerDataImport() {

		return ResponseEntity.ok(new ApiResponseDTO<>(importerService.getFileList()));
	}
}

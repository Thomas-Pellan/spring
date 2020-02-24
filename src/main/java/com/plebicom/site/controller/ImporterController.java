package com.plebicom.site.controller;

import com.plebicom.service.OpenApiImporterService;
import com.plebicom.site.dto.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/importer")
@CrossOrigin
public class ImporterController {

	@Autowired
	private OpenApiImporterService openApiImporterService;

	@GetMapping(path="/article")
	public @ResponseBody ResponseEntity triggerDataImport() {
		openApiImporterService.ImportOpenArticles();
		return ResponseEntity.ok(new ApiResponseDTO<>(null));
	}
}

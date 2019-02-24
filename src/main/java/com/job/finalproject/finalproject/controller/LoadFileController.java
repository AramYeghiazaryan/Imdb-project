package com.job.finalproject.finalproject.controller;

import com.job.finalproject.finalproject.service.LoadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/load")
public class LoadFileController {

    private final LoadFileService loadFileService;
    @Autowired
    public LoadFileController(LoadFileService loadFileService) {
        this.loadFileService=loadFileService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public void loadFile() throws IOException {
       loadFileService.importFile();
    }
}

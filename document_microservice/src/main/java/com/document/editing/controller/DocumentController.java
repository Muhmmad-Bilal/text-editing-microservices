package com.document.editing.controller;

import com.document.editing.dto.requestdto.DocumentRequestDTO;
import com.document.editing.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/document")
public class DocumentController {
   @Autowired
    private DocumentService documentService;


    @PostMapping(value = "/create")
    public String createFile(@RequestBody  DocumentRequestDTO documentRequestDTO) {
        System.out.println("hit the test");
        return documentService.createFile(documentRequestDTO);
    }

}

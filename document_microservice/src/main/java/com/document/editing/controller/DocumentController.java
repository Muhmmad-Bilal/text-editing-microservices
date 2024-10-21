package com.document.editing.controller;

import com.document.editing.dto.requestdto.DocumentRequestDTO;
import com.document.editing.dto.requestdto.FileRequestDTO;
import com.document.editing.dto.requestdto.RevertVersionRequestDTO;
import com.document.editing.service.DocumentService;
import com.document.editing.service.FileReadingService;
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
    @Autowired
    FileReadingService fileReadingService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createFile(@RequestBody  DocumentRequestDTO documentRequestDTO) {
        return ResponseEntity.ok(documentService.createFile(documentRequestDTO));
    }

    @PostMapping(value ="/open-file")
    public  void openFile(@RequestBody FileRequestDTO fileRequestDTO)
    {
           fileReadingService.startWatching(fileRequestDTO);
    }
    @PostMapping(value = "/revert-version")
    public ResponseEntity<String> revertVersion(@RequestBody RevertVersionRequestDTO revertVersionRequestDTO)
    {
       return ResponseEntity.ok(fileReadingService.revertToVersion(revertVersionRequestDTO));
    }

}

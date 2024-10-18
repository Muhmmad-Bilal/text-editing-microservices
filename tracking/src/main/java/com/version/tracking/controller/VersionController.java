package com.version.tracking.controller;

import com.version.tracking.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/version")
public class VersionController {
    @Autowired
    private VersionService documentVersionService;
    @PostMapping("/save")
    public ResponseEntity<String> saveVersion(@RequestBody Map<String, Object> request) {
        try {
            String content = (String) request.get("content");
            Long documentId = Long.valueOf((Integer) request.get("documentId"));
            Long modifiedBy = Long.valueOf((Integer) request.get("modifiedBy"));
            documentVersionService.saveNewVersion(content, documentId, modifiedBy);
            return ResponseEntity.ok("Document version saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save document version");
        }
    }

}

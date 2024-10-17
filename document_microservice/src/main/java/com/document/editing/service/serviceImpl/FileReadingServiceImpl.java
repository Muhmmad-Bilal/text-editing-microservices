package com.document.editing.service.serviceImpl;

import com.document.editing.dto.responsedto.DocumentVersionResponseDTO;
import com.document.editing.entity.DocumentEntity;
import com.document.editing.mapper.DocumentMapper;
import com.document.editing.repository.DocumentRepository;
import com.document.editing.service.FileReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileReadingServiceImpl implements FileReadingService {
    private final String directoryPath = "D:/"; // Directory to watch
    private final String fileName = "test.txt"; // File to monitor for changes

    @Autowired
    private RestTemplate restTemplate;

    private final String versionServiceUrl = "http://localhost:8082/version/retrieve";
    @Autowired
   private DocumentMapper documentMapper;
    @Autowired
   private DocumentRepository documentRepository;

    @Override
    public void startWatching() {

        try {

            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(directoryPath);
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            System.out.println("Watching directory: " + directoryPath);
            while (true) {
                WatchKey key;
                try {
                    key = watchService.take();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    // We only care about modify events
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Path changed = (Path) event.context();
                        if (changed.endsWith(fileName)) {
                            System.out.println(fileName + " has been modified.");
                            readFileContent();
                        }
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void revertToVersion(Long documentId, int versionNumber, Long modifiedBy) {
        ResponseEntity<DocumentVersionResponseDTO> response = restTemplate.getForEntity(
                versionServiceUrl + "?documentId=" + documentId + "&versionNumber=" + versionNumber,
                DocumentVersionResponseDTO.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to retrieve version");
        }
        DocumentVersionResponseDTO documentVersion = response.getBody();

        DocumentEntity document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        document.setContent(documentVersion.getContent());
        document.setLastUpdateBy(modifiedBy);
        document.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        documentRepository.save(document);
        saveDocumentVersion(documentVersion.getContent(), documentId, modifiedBy);

    }

    private void readFileContent() {
        try {
            Path filePath = Paths.get(directoryPath, fileName);
            String content = Files.readString(filePath);

         DocumentEntity documentEntity= documentRepository.save(documentMapper.savedocument(content, 4L));
            saveDocumentVersion(content,documentEntity.getId(),documentEntity.getUserId());
            System.out.println("File content:\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveDocumentVersion(String content, Long documentId, Long modifiedBy) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/version/save";
        Map<String, Object> request = new HashMap<>();
        request.put("content", content);
        request.put("documentId", documentId);
        request.put("modifiedBy", modifiedBy);
        restTemplate.postForObject(url, request, Void.class);
    }

    }

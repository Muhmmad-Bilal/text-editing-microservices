package com.document.editing.service;

public interface FileReadingService {
    public void startWatching() ;
    public void revertToVersion(Long documentId, int versionNumber, Long modifiedBy);

}

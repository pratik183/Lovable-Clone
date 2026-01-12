package com.lovable.com.lovable.service;

import com.lovable.com.lovable.dto.project.FileContentResponse;
import com.lovable.com.lovable.dto.project.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}

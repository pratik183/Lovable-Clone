package com.lovable.com.lovable.service.impl;

import com.lovable.com.lovable.dto.project.FileContentResponse;
import com.lovable.com.lovable.dto.project.FileNode;
import com.lovable.com.lovable.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of(); // TODO: implement file tree retrieval with access checks
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null; // TODO: load file content from storage after validating project access
    }
}

package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.Tag;
import com.softwareengineering.planai.domain.entity.Task;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.web.dto.TaskUpdateDto;
import com.softwareengineering.planai.web.repository.TagRepository;
import com.softwareengineering.planai.web.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Tag addSchedule(Tag tag) {
        tagRepository.save(tag);
        return tag;
    }

    @Transactional
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Transactional
    public List<Tag> findByOwner(User owner) {
        List<Tag> taskList = tagRepository.findByOwner(owner);
        return taskList;
    }

    @Transactional
    public Optional<Tag> findByName(String name) {
        List<Tag> tagList = tagRepository.findByTagName(name);
        if(tagList.size() > 1) {
            throw new IllegalStateException("중복되는 태그가 존재함");
        }
        if(tagList.size() == 0) {
            throw new IllegalStateException("태그가 존재하지 않음");
        }
        return Optional.ofNullable(tagList.get(0));
    }

    @Transactional
    public Long deleteSchedule(Long id) {
        tagRepository.deleteById(id);
        return id;
    }
}

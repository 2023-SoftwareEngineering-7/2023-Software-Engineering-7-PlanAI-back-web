package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.Tag;
import com.softwareengineering.planai.domain.entity.Task;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.domain.mapping.TaskTag;
import com.softwareengineering.planai.web.dto.update.TaskUpdateDto;
import com.softwareengineering.planai.web.repository.TagRepository;
import com.softwareengineering.planai.web.repository.TaskRepository;
import com.softwareengineering.planai.web.repository.TaskTagRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private TagRepository tagRepository;
    private TaskTagRepository taskTagRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TagRepository tagRepository, TaskTagRepository taskTagRepository) {
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
        this.taskTagRepository = taskTagRepository;
    }

    @Transactional
    public Task addTask(Task task, List<String> tagList) {
        taskRepository.save(task);
        for (String tagName : tagList) {
            try {
                Tag tag = tagRepository.findByTagName(tagName).get(0);
                TaskTag st = TaskTag.builder()
                        .task(task)
                        .tag(tag)
                        .build();
                taskTagRepository.save(st);
                task.getTagList().add(st);
            }catch(IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("잘못된 태그명");
            }
        }
        return task;
    }

    @Transactional
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public List<Task> findByOwner(User owner) {
        List<Task> taskList = taskRepository.findByOwner(owner);
        return taskList;
    }

    @Transactional
    public Task updateTaskInfo(Long id, TaskUpdateDto dto) {
        Task targetSchedule = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 스케쥴을 찾을 수 없음"));
        targetSchedule.updateTask(dto);
        return targetSchedule;
    }

    @Transactional
    public Long deleteTask(Long id) {
        taskRepository.deleteById(id);
        return id;
    }
}

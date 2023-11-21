package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.Task;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.web.dto.ScheduleUpdateDto;
import com.softwareengineering.planai.web.dto.TaskUpdateDto;
import com.softwareengineering.planai.web.repository.ScheduleRepository;
import com.softwareengineering.planai.web.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task addSchedule(Task task) {
        taskRepository.save(task);
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
    public Task updateScheduleInfo(Long id, TaskUpdateDto dto) {
        Task targetSchedule = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 스케쥴을 찾을 수 없음"));
        targetSchedule.updateTask(dto);
        return targetSchedule;
    }

    @Transactional
    public Long deleteSchedule(Long id) {
        taskRepository.deleteById(id);
        return id;
    }
}

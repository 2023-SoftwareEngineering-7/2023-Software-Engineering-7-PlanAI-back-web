package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.Task;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.web.dto.request.ScheduleRegisterDto;
import com.softwareengineering.planai.web.dto.request.TaskRegisterDto;
import com.softwareengineering.planai.web.dto.response.ScheduleResponseDto;
import com.softwareengineering.planai.web.dto.response.TaskResponseDto;
import com.softwareengineering.planai.web.dto.update.ScheduleUpdateDto;
import com.softwareengineering.planai.web.dto.update.TaskUpdateDto;
import com.softwareengineering.planai.web.service.ScheduleService;
import com.softwareengineering.planai.web.service.TagService;
import com.softwareengineering.planai.web.service.TaskService;
import com.softwareengineering.planai.web.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/user/{userId}/Task")
    public List<TaskResponseDto> getTask(@PathVariable(name = "userId") Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));

        List<Task> taskList = taskService.findByOwner(user);

        return taskList.stream()
                .map(TaskResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/user/{userId}/Schedule")
    public TaskResponseDto registerSchedule(@PathVariable(name = "userId") Long userId, @RequestBody TaskRegisterDto dto) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));

        Task schedule = Task.builder()
                .title(dto.getTitle())
                .deadline(dto.getDeadline())
                .owner(user)
                .build();

        taskService.addTask(schedule, dto.getTagList());

        return new TaskResponseDto(taskService.addTask(schedule, dto.getTagList()));
    }

    @PutMapping("/user/{userId}/Schedule/{scheduleId}")
    public TaskResponseDto updateSchedule(@PathVariable("userId") Long id,
                                      @PathVariable("scheduleId") Long taskId,
                                      @RequestBody TaskUpdateDto dto) {
        return new TaskResponseDto(taskService.updateTaskInfo(taskId, dto));
    }

    @DeleteMapping("/user/{userId}/Schedule/{scheduleId}")
    public Long deleteSchedule(@PathVariable("userId") Long id, @PathVariable("scheduleId") Long taskId) {
        return taskService.deleteTask(taskId);
    }
}

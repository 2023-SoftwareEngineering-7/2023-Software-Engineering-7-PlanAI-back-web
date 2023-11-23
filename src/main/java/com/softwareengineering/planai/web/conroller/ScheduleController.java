package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.Tag;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.web.dto.request.ScheduleRegisterDto;
import com.softwareengineering.planai.web.dto.request.UserRegisterDto;
import com.softwareengineering.planai.web.dto.response.FriendListDto;
import com.softwareengineering.planai.web.dto.response.ScheduleResponseDto;
import com.softwareengineering.planai.web.dto.response.UserResponseDto;
import com.softwareengineering.planai.web.dto.update.ScheduleUpdateDto;
import com.softwareengineering.planai.web.dto.update.UserUpdateDto;
import com.softwareengineering.planai.web.service.ScheduleService;
import com.softwareengineering.planai.web.service.TagService;
import com.softwareengineering.planai.web.service.UserService;
import java.util.ArrayList;
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
public class ScheduleController {

    private ScheduleService scheduleService;
    private UserService userService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, UserService userService) {
        this.scheduleService = scheduleService;
        this.userService = userService;
    }


    @GetMapping("/user/{userId}/Schedule")
    public List<ScheduleResponseDto> getSchedule(@PathVariable(name = "userId") Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));

        List<Schedule> scheduleList = scheduleService.findByOwner(user);

        return scheduleList.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/user/{userId}/Schedule")
    public ScheduleResponseDto registerSchedule(@PathVariable(name = "userId") Long userId, @RequestBody ScheduleRegisterDto dto) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));

        Schedule schedule = Schedule.builder()
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .owner(user)
                .build();

        scheduleService.addSchedule(schedule, dto.getTagList());

        return new ScheduleResponseDto(scheduleService.addSchedule(schedule, dto.getTagList()));
    }

    @PutMapping("/user/{userId}/Schedule/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable("userId") Long id,
                                      @PathVariable("scheduleId") Long scheduleId,
                                      @RequestBody ScheduleUpdateDto dto) {
        return new ScheduleResponseDto(scheduleService.updateScheduleInfo(id, dto));
    }

    @DeleteMapping("/user/{userId}/Schedule/{scheduleId}")
    public Long deleteSchedule(@PathVariable("userId") Long id, @PathVariable("scheduleId") Long scheduleId) {
        return scheduleService.deleteSchedule(scheduleId);
    }
}
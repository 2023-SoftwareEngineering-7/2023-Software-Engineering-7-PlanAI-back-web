package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.UserFriend;
import com.softwareengineering.planai.web.dto.ScheduleUpdateDto;
import com.softwareengineering.planai.web.dto.UserUpdateDto;
import com.softwareengineering.planai.web.repository.ScheduleRepository;
import com.softwareengineering.planai.web.repository.UserFriendRepository;
import com.softwareengineering.planai.web.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public Schedule addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Transactional
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Transactional
    public List<Schedule> findByOwner(User owner) {
        List<Schedule> scheduleList = scheduleRepository.findByOwner(owner);
        return scheduleList;
    }

    @Transactional
    public Schedule updateScheduleInfo(Long id, ScheduleUpdateDto dto) {
        Schedule targetSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 스케쥴을 찾을 수 없음"));
        targetSchedule.updateScheduleInfo(dto);
        return targetSchedule;
    }

    @Transactional
    public Long deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
        return id;
    }
}

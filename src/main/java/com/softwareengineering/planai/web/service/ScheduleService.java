package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.Tag;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.web.dto.update.ScheduleUpdateDto;
import com.softwareengineering.planai.web.repository.ScheduleRepository;
import com.softwareengineering.planai.web.repository.ScheduleTagRepository;
import com.softwareengineering.planai.web.repository.TagRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private TagRepository tagRepository;
    private ScheduleTagRepository scheduleTagRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository,
                           TagRepository tagRepository,
                           ScheduleTagRepository scheduleTagRepository) {
        this.scheduleRepository = scheduleRepository;
        this.tagRepository = tagRepository;
        this.scheduleTagRepository = scheduleTagRepository;
    }

    @Transactional
    public Schedule addSchedule(Schedule schedule, List<String> tagList) {
        scheduleRepository.save(schedule);
        for (String tagName : tagList) {
            try {
                Tag tag = tagRepository.findByTagName(tagName).get(0);
                ScheduleTag st = ScheduleTag.builder()
                        .schedule(schedule)
                        .tag(tag)
                        .build();
                scheduleTagRepository.save(st);
                schedule.getTagList().add(st);
            }catch(IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("잘못된 태그명");
            }
        }
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

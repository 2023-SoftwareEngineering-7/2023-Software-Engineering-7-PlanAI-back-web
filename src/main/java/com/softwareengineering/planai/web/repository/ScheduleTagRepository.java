package com.softwareengineering.planai.web.repository;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.domain.mapping.UserFriend;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTagRepository extends JpaRepository<ScheduleTag, Long> {
    public List<ScheduleTag> findBySchedule(Schedule schedule);
}

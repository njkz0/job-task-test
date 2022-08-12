package com.firstspringapplication.dao;

import com.firstspringapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDAO extends JpaRepository <Task, Integer> {
    List<Task> findAllByCreatorId(Integer creatorId);
}

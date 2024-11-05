package com.exo1.exo1.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MaterializedViewService {

    private final JdbcTemplate jdbcTemplate;

    public MaterializedViewService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to refresh the materialized view
    public void refreshTasksPerProjectView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW tasks_per_project");
    }

    // Scheduled method to refresh the materialized view every hour
    @Scheduled(fixedRate = 3600000) // 3600000 ms = 1 hour
    public void scheduledRefresh() {
        refreshTasksPerProjectView();
        System.out.println("Materialized view 'tasks_per_project' refreshed.");
    }
}
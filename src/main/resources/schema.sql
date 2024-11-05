CREATE MATERIALIZED VIEW tasks_per_project AS
SELECT p.id AS project_id, p.name AS project_name, COUNT(t.id) AS task_count
FROM projet p
         LEFT JOIN task t ON t.projet_id = p.id
GROUP BY p.id, p.name;
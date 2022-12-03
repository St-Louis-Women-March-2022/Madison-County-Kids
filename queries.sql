-- Part 1: Test it with SQL
//id, employer, name, skills, skill, employer_id
-- Part 2: Test it with SQL
SELECT name FROM employer WHERE location = "St. Louis City";
-- Part 3: Test it with SQL
DROP TABLE JOB;
-- Part 4: Test it with
Select * From skill
* left join job_skills on
skill.id = job_skills.skills_id
* Where job_skills.jobs_id is not null
* order by name ASC;
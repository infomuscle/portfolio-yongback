package com.yongback.portfolio.domain.repository

import com.yongback.portfolio.domain.constant.SkillType
import com.yongback.portfolio.domain.entity.Project
import com.yongback.portfolio.domain.entity.ProjectDetail
import com.yongback.portfolio.domain.entity.ProjectSkill
import com.yongback.portfolio.domain.entity.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
    @Autowired val skillRepository: SkillRepository
) {

    val DATA_SIZE = 10

    // 더미 객체 생성
    private fun createProject(n: Int): Project {
        val project = Project(
            name = "${n}",
            description = "테스트 설명 {n}",
            startYear = 2023,
            startMonth = 9,
            endYear = 2023,
            endMonth = 9,
            isActive = true
        )

        val details = mutableListOf<ProjectDetail>()
        for (i in 1..n) {
            val projectDetail = ProjectDetail(content = "테스트 ${i}", url = null, isActive = true)
            details.add(projectDetail)
        }
        project.addDetails(details)

        val skills = skillRepository.findAll()
        val skillsUsedInProject = skills.subList(0, n)
        for (skill in skillsUsedInProject) {
            val projectSkill = ProjectSkill(project = project, skill = skill)
            project.skills.add(projectSkill)
        }

        return project
    }

    @BeforeAll
    fun beforeAll() {
        println("----- 스킬 데이터 초기화 시작 -----")
        val skills = mutableListOf<Skill>()
        for (i in 1..DATA_SIZE) {
            val skillTypes = SkillType.values()
            val skill = Skill(name = "테스트 ${i}", type = skillTypes[i % skillTypes.size].name, isActive = true)
            skills.add(skill)
        }
        skillRepository.saveAll(skills)
        println("----- 스킬 데이터 초기화 종료 -----")

        println("----- 데이터 초기화 이전 조회 시작 -----")
        val beforeInsert = projectRepository.findAll()
        assertThat(beforeInsert).hasSize(0)
        println("----- 데이터 초기화 이전 조회 종료 -----")

        println("----- 테스트 데이터 초기화 시작 -----")
        val projects = mutableListOf<Project>()
        for (i in 1..DATA_SIZE) {
            val project = createProject(i)
            projects.add(project)
        }
        projectRepository.saveAll(projects)
        println("----- 테스트 데이터 초기화 종료 -----")
    }

    @Test
    fun testFindAll() {
        println("----- findAll 테스트 시작 -----")
        val projects = projectRepository.findAll()
        assertThat(projects).hasSize(DATA_SIZE)
        println("projects.size: ${projects.size}")

        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            println("project.details.size: ${project.details.size}")

            assertThat(project.skills).hasSize(project.name.toInt())
            println("project.skills.size: ${project.skills.size}")
        }
        println("----- findAll 테스트 종료 -----")
    }

    @Test
    fun testFindAllByIsActive() {
        println("----- findAllByIsActive 테스트 시작 -----")
        val projects = projectRepository.findAllByIsActive(true)
        assertThat(projects).hasSize(DATA_SIZE)
        println("projects.size: ${projects.size}")

        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            println("project.details.size: ${project.details.size}")

            assertThat(project.skills).hasSize(project.name.toInt())
            println("project.skills.size: ${project.skills.size}")
        }
        println("----- findAllByIsActive 테스트 종료 -----")
    }
}

package ru.vyarus.gradle.plugin.animalsniffer

import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException
import org.gradle.testfixtures.ProjectBuilder

/**
 * @author Vyacheslav Rusakov
 * @since 13.12.2015
 */
class AnimalSnifferPluginTest extends AbstractTest {

    def "Check extension registration"() {

        when: "plugin applied"
        Project project = ProjectBuilder.builder().build()
        project.plugins.apply "ru.vyarus.animalsniffer"

        then: "extension registered"
        project.extensions.findByType(AnimalSnifferExtension)

    }

    def "Check extension validation"() {

        when: "plugin configured"
        Project project = project {
            apply plugin: "java"
            apply plugin: "ru.vyarus.animalsniffer"
        }

        then: "tasks registered"
        project.tasks.withType(AnimalSniffer).size() == 2
    }

    def "Check scope reduce"() {

        when: "plugin configured"
        Project project = project {
            apply plugin: "java"
            apply plugin: "ru.vyarus.animalsniffer"

            animalsniffer {
                sourceSets = [sourceSets.main]
            }
        }

        then: "task registered"
        project.tasks.withType(AnimalSniffer).size() == 2
    }
}
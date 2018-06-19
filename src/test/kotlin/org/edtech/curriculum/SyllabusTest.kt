package org.edtech.curriculum

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class SyllabusTest {

    @Test
    fun testGetSubjectsHtmlGR() {
        testGetSubjectsHtml(SyllabusType.GR)
    }
    @Test
    fun testGetSubjectsHtmlGRS() {
        testGetSubjectsHtml(SyllabusType.GRS)
    }
    @Test
    fun testGetSubjectsHtmlGY() {
        testGetSubjectsHtml(SyllabusType.GY)
    }
    @Test
    fun testGetSubjectsHtmlGYS() {
        testGetSubjectsHtml(SyllabusType.GYS)
    }
    @Test
    fun testGetSubjectsHtmlVUXGR() {
        testGetSubjectsHtml(SyllabusType.VUXGR)
    }
/*    @Test
    fun testGetSubjectsHtmlSFI() {
        testGetSubjectsHtml(SyllabusType.SFI)
    }*/
    @Test
    fun testGetSubjectsGR() {
        testGetSubjects(SyllabusType.GR)
    }
    @Test
    fun testGetSubjectsGRS() {
        testGetSubjects(SyllabusType.GRS)
    }
    @Test
    fun testGetSubjectsGY() {
        testGetSubjects(SyllabusType.GY)
    }
    @Test
    fun testGetSubjectsGYS() {
        testGetSubjects(SyllabusType.GYS)
    }
    @Test
    fun testGetSubjectsVUXGR() {
        testGetSubjects(SyllabusType.VUXGR)
    }
/*    @Test
    fun testGetSubjectsSFI() {
        testGetSubjects(SyllabusType.SFI)
    }
*/
    private fun testGetSubjectsHtml(syllabusType: SyllabusType) {
        Syllabus(syllabusType, File("./src/test/resources/opendata/")).subjectHtml.forEach {
            assertTrue("${syllabusType.name}/${it.code} has no name", it.name.isNotEmpty())
            assertTrue("${syllabusType.name}/${it.name} has no code", it.code.isNotEmpty())
            assertTrue("${syllabusType.name}/${it.skolfsId} has no skolfsId", it.skolfsId.isNotEmpty())
            assertTrue("${syllabusType.name}/${it.name} has no courses", it.courses.isNotEmpty())
            assertTrue("${syllabusType.name}/${it.name} has no purposes", it.purposes.isNotEmpty())

            it.courses.forEach { courseHtml ->
                // Only require kr when passed the lowest grades
                if (!courseHtml.year.startsWith("1-")) {
                    assertTrue("${courseHtml.code}/${courseHtml.name} has no knowledgeRequirements", courseHtml.knowledgeRequirement.isNotEmpty())
                }
                assertTrue("${courseHtml.code}/${courseHtml.name} has no centralContents", courseHtml.centralContent.isNotEmpty())
                assertTrue("${courseHtml.code}/${courseHtml.name} has malformed centralContents", courseHtml.centralContent.contains("<li>"))
                assertTrue("${courseHtml.code}/${courseHtml.name} has no code", courseHtml.code.isNotEmpty())
                assertTrue("${courseHtml.code}/${courseHtml.name} has no name", courseHtml.name.isNotEmpty())

                // Only check real courses
                if (courseHtml.year.isEmpty() ) {
                    if (courseHtml.point.isEmpty()) {
                        fail("${courseHtml.code}/${courseHtml.name} has no points/year group")
                    }
                    assertTrue("${courseHtml.code}/${courseHtml.name} has no description", courseHtml.description.isNotEmpty())
                }
            }
        }
    }
    @Test
    fun testDuplicateRequirementsGR() {
        Syllabus(SyllabusType.GR, File("./src/test/resources/opendata/")).subjectHtml.forEach {

            it.courses.forEach { courseHtml ->
                courseHtml.knowledgeRequirement.forEach {entry ->
                    assertFalse("duplicate knowledge requirement found in ${it.name}/${courseHtml.name}" , it.courses.filter { c -> courseHtml != c }.map{ c -> c.knowledgeRequirement[entry.key]}.contains(entry.value))
                }
            }
        }
    }

    private fun testPurpose(name: String, purposes: List<Purpose>) {
        assertTrue("$name has no purpose", purposes.isNotEmpty() )

        purposes.forEach {
            assertTrue( "Found empty purpose in $name", it.lines.isNotEmpty() || it.heading.isNotEmpty())
            assertNull( "Found empty purpose line in $name", it.lines.firstOrNull { it.trim().isEmpty() })
            if (it.type == PurposeType.BULLET) {
                assertTrue( "Bullet lists always needs a heading $name", it.heading.isNotEmpty())
            }
        }
    }

    private fun testCentralContent(name: String, centralContents: List<CentralContent>) {
        assertTrue("$name has no central contents", centralContents.isNotEmpty() )

        centralContents.forEach {
            assertTrue( "Found empty central contents in $name", it.lines.isNotEmpty() || it.heading.isNotEmpty())
            assertNull( "Found empty central contents line in $name", it.lines.firstOrNull { it.trim().isEmpty() })
        }
    }

    private fun testGetSubjects(syllabusType: SyllabusType) {
        Syllabus(syllabusType, File("./src/test/resources/opendata/")).getSubjects().forEach {
            assertTrue("${syllabusType.name}/${it.name} has no name", it.name.isNotEmpty())
            assertTrue("${syllabusType.name}/${it.name} has no skolfsId", it.skolfsId.isNotEmpty())
            assertTrue("${syllabusType.name}/${it.name} has no code", it.code.isNotEmpty())
            assertTrue("${syllabusType.name}/${it.name} has no courses", it.courses.isNotEmpty())
            testPurpose("${syllabusType.name}/${it.name}", it.purposes)

            it.courses.forEach { course ->
                testCentralContent("${course.code}/${course.name}", course.centralContent)
                assertTrue("${course.code}/${course.name} has no knowledgeRequirements", course.knowledgeRequirementParagraphs.isNotEmpty())
                assertTrue("${course.code}/${course.name} has no code", course.code.isNotEmpty())
                assertTrue("${course.code}/${course.name} has no name", course.name.isNotEmpty())
                // Only check real courses
                if (course.year == null ) {
                    if (course.point == null) {
                        fail("${course.code}/${course.name} has no points/year group")
                    }
                    assertTrue("${course.code}/${course.name} has no description", course.description.isNotEmpty())
                    if (course.point != null) {
                        assertTrue("${course.code}/${course.name} has no points", course.point!! > 0)
                    } else {
                        fail("${course.code}/${course.name} has no points")
                    }
                }
            }
        }
    }
}

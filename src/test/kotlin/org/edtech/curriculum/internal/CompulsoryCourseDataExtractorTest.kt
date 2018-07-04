package org.edtech.curriculum.internal

import org.edtech.curriculum.GradeStep
import org.edtech.curriculum.stringToRange
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File



internal class CompulsoryCourseDataExtractorTest {
    private val courseDataExtractor: CompulsoryCourseDataExtractor = CompulsoryCourseDataExtractor(Jsoup.parse(File("./src/test/resources/extracted/Biologi.xml").inputStream(), null, "", Parser.xmlParser()))
    private val courseDataExtractorMod: CompulsoryCourseDataExtractor = CompulsoryCourseDataExtractor(Jsoup.parse(File("./src/test/resources/extracted/Moderna sprak.xml").inputStream(), null, "", Parser.xmlParser()))

    @Test
    fun getCourseData() {
    }

    @Test
    fun testKnowledgeRequirementExtraction() {
        assertEquals(mapOf(
                GradeStep.G to """<h3>Kunskapskrav för godtagbara kunskaper i slutet av årskurs 3</h3><p>Eleven kan beskriva och ge exempel på enkla samband i naturen utifrån upplevelser och utforskande av närmiljön. I samtal om årstider berättar eleven om förändringar i naturen och ger exempel på livscykler hos några djur och växter. Eleven berättar också om några av människans kroppsdelar och sinnen, och diskuterar några faktorer som påverkar människors hälsa. Eleven kan samtala om tyngdkraft, friktion och jämvikt i relation till lek och rörelse. Eleven beskriver vad några olika föremål är tillverkade av för material och hur de kan sorteras. Eleven kan berätta om ljus och ljud och ge exempel på egenskaper hos vatten och luft och relatera till egna iakttagelser. Dessutom kan eleven samtala om skönlitteratur, myter och konst som handlar om naturen och människan.</p><p>Utifrån tydliga instruktioner kan eleven utföra fältstudier och andra typer av enkla undersökningar som handlar om naturen och människan, kraft och rörelse samt vatten och luft. Eleven gör enkla observationer av årstider, namnger några djur och växter, sorterar dem efter olika egenskaper samt beskriver och ger exempel på kopplingar mellan dem i enkla näringskedjor. Eleven kan visa och beskriva hur solen, månen och jorden rör sig i förhållande till varandra. Eleven kan sortera några föremål utifrån olika egenskaper samt separerar lösningar och blandningar med enkla metoder. I det undersökande arbetet gör eleven någon jämförelse mellan egna och andras resultat. Eleven dokumenterar dessutom sina undersökningar med hjälp av olika uttrycksformer och kan använda sig av sin dokumentation i diskussioner och samtal.</p>"""),
                courseDataExtractor.getKnowledgeRequirements(1..3, "").first().knowledgeRequirements)
        assertEquals(mapOf(
                GradeStep.E to """<h3>Kunskapskrav för betyget E i slutet av årskurs 6</h3><p>Eleven kan samtala om och diskutera enkla frågor som rör hälsa, naturbruk och ekologisk hållbarhet genom att ställa frågor och framföra och bemöta åsikter på ett sätt som <strong>till viss del för samtalen och diskussionerna framåt</strong>. Eleven kan söka naturvetenskaplig information och använder då olika källor och för <strong>enkla </strong>resonemang om informationens och källornas användbarhet. Eleven kan använda informationen i diskussioner och för att skapa texter och andra framställningar med <strong>viss </strong>anpassning till sammanhanget.</p><p>Eleven kan genomföra enkla fältstudier och andra undersökningar utifrån givna planeringar och även <strong>bidra till att formulera</strong> enkla frågeställningar och planeringar som det går att arbeta systematiskt utifrån. I arbetet använder eleven utrustning på ett säkert och <strong>i huvudsak fungerande </strong>sätt. Eleven kan jämföra sina och andras resultat och för då <strong>enkla</strong> resonemang om likheter och skillnader och vad de kan bero på samt <strong>bidrar till att ge förslag</strong> som kan förbättra undersökningen. Dessutom gör eleven <strong>enkla</strong> dokumentationer av sina undersökningar i text och bild.</p><p>Eleven har <strong>grundläggande</strong> kunskaper om biologiska sammanhang och visar det genom att <strong>ge exempel på och beskriva </strong>dessa med <strong>viss </strong>användning av biologins begrepp. I <strong>enkla och till viss del </strong>underbyggda resonemang om hälsa, sjukdom och pubertet kan eleven relatera till några samband i människokroppen. Eleven kan också <strong>beskriva och ge exempel</strong> <strong>på </strong>människors beroende av och påverkan på naturen och gör då kopplingar till organismers liv och ekologiska samband. Dessutom berättar eleven om livets utveckling och <strong>ger exempel</strong> <strong>på </strong>organismers anpassningar till olika livsmiljöer. Eleven kan också berätta om några naturvetenskapliga upptäckter och deras betydelse för människors levnadsvillkor.</p>""",
                GradeStep.D to """<h3>Kunskapskrav för betyget D i slutet av årskurs 6</h3><p>Betyget D innebär att kunskapskraven för betyget E och till övervägande del för C är uppfyllda.</p>""",
                GradeStep.C to """<h3>Kunskapskrav för betyget C i slutet av årskurs 6</h3><p>Eleven kan samtala om och diskutera enkla frågor som rör hälsa, naturbruk och ekologisk hållbarhet genom att ställa frågor och framföra och bemöta åsikter på ett sätt som<strong> för samtalen och diskussionerna framåt</strong>. Eleven kan söka naturvetenskaplig information och använder då olika källor och för <strong>utvecklade </strong>resonemang om informationens och källornas användbarhet. Eleven kan använda informationen i diskussioner och för att skapa texter och andra framställningar med <strong>relativt</strong> <strong>god </strong>anpassning till sammanhanget.</p><p>Eleven kan genomföra enkla fältstudier och andra undersökningar utifrån givna planeringar och även <strong>formulera</strong> enkla frågeställningar och planeringar som det <strong>efter någon bearbetning</strong> går att arbeta systematiskt utifrån. I arbetet använder eleven utrustning på ett säkert och <strong>ändamålsenligt</strong> sätt. Eleven kan jämföra sina och andras resultat och för då <strong>utvecklade</strong> resonemang om likheter och skillnader och vad de kan bero på samt <strong>ger förslag som efter någon bearbetning </strong>kan förbättra undersökningen. Dessutom gör eleven <strong>utvecklade</strong> dokumentationer av sina undersökningar i text och bild.</p><p>Eleven har <strong>goda</strong> kunskaper om biologiska sammanhang och visar det genom att <strong>förklara</strong> och <strong>visa på enkla samband </strong><strong>inom </strong>dessa med <strong>relativt god </strong>användning av biologins begrepp. I <strong>utvecklade och </strong><strong>relativt väl</strong> underbyggda resonemang om hälsa, sjukdom och pubertet kan eleven relatera till några samband i människokroppen. Eleven kan också <strong>förklara</strong> och <strong>visa på samband</strong> <strong>mellan </strong>människors beroende av och påverkan på naturen, och gör då kopplingar till organismers liv och ekologiska samband. Dessutom berättar eleven om livets utveckling och <strong>visar på samband kring</strong> organismers anpassningar till olika livsmiljöer. Eleven kan också berätta om några naturvetenskapliga upptäckter och deras betydelse för människors levnadsvillkor.</p>""",
                GradeStep.B to """<h3>Kunskapskrav för betyget B i slutet av årskurs 6</h3><p>Betyget B innebär att kunskapskraven för betyget C och till övervägande del för A är uppfyllda.</p>""",
                GradeStep.A to """<h3>Kunskapskrav för betyget A i slutet av årskurs 6</h3><p>Eleven kan samtala om och diskutera enkla frågor som rör hälsa, naturbruk och ekologisk hållbarhet genom att ställa frågor och framföra och bemöta åsikter på ett sätt som<strong> för samtalen och diskussionerna framåt och fördjupar eller breddar dem</strong>. Eleven kan söka naturvetenskaplig information och använder då olika källor och för <strong>välutvecklade </strong>resonemang om informationens och källornas användbarhet. Eleven kan använda informationen i diskussioner och för att skapa texter och andra framställningar med <strong>god </strong>anpassning till sammanhanget.</p><p>Eleven kan genomföra enkla fältstudier och andra undersökningar utifrån givna planeringar och även <strong>formulera</strong> enkla frågeställningar och planeringar som det går att arbeta systematiskt utifrån. I arbetet använder eleven utrustning på ett säkert, <strong>ändamålsenligt och effektivt</strong> sätt. Eleven kan jämföra sina och andras resultat och för då <strong>välutvecklade</strong> resonemang om likheter och skillnader och vad de kan bero på samt <strong>ger förslag </strong>som kan förbättra undersökningen. Dessutom gör eleven <strong>välutvecklade</strong> dokumentationer av sina undersökningar i text och bild.</p><p>Eleven har <strong>mycket goda</strong> kunskaper om biologiska sammanhang och visar det genom att <strong>förklara </strong>och <strong>visa på enkla samband</strong> <strong>inom </strong>dessa <strong>och </strong><strong>något gemensamt drag </strong>med <strong>god användning</strong> av biologins begrepp. I <strong>välutvecklade och väl </strong>underbyggda resonemang om hälsa, sjukdom och pubertet kan eleven relatera till några samband i människokroppen. Eleven kan också <strong>förklara</strong> och <strong>visa</strong> <strong>på</strong> <strong>mönster</strong> <strong>hos </strong>människors beroende av och påverkan på naturen, och gör då kopplingar till organismers liv och ekologiska samband. Dessutom berättar eleven om livets utveckling och <strong>visar på mönster i</strong> organismers anpassningar till olika livsmiljöer. Eleven kan också berätta om några naturvetenskapliga upptäckter och deras betydelse för människors levnadsvillkor.</p>"""),
                courseDataExtractor.getKnowledgeRequirements(4..6, "").first().knowledgeRequirements)
        assertEquals(mapOf(
                GradeStep.E to """<h3>Inom ramen för elevens val</h3><h3>Kunskapskrav för betyget E i slutet av årskurs 9</h3><p>Eleven kan förstå <strong>vanliga ord och enkla fraser</strong> i tydligt talat, enkelt språk i långsamt tempo samt i korta, enkla texter om vardagliga och välbekanta ämnen. Eleven visar sin förståelse genom att<strong> i mycket enkel form</strong> redogöra för innehållet samt genom att med <strong>godtagbart </strong>resultat agera utifrån budskap och instruktioner i innehållet. För att underlätta sin förståelse av innehållet i det talade språket och texterna kan eleven välja och använda sig av <strong>någon strategi </strong>för lyssnande och läsning. Eleven kan välja texter och talat språk av enkel karaktär och från olika medier samt <strong>med viss relevans</strong> använda det valda materialet i sin egen produktion och interaktion.</p><p>I muntliga och skriftliga framställningar av olika slag kan eleven formulera sig <strong>enkelt och</strong> <strong>begripligt med enstaka ord och fraser</strong>. För att förtydliga och variera sin kommunikation kan eleven bearbeta och göra <strong>enstaka enkla</strong> förbättringar av egna framställningar. I muntlig och skriftlig interaktion kan eleven uttrycka sig <strong>enkelt och</strong><strong> begripligt med enstaka ord och fraser</strong>. Dessutom kan eleven välja och använda sig av <strong>någon</strong> <strong>strategi </strong>som löser problem i och förbättrar interaktionen.</p><p>Eleven kommenterar<strong> i</strong><strong> mycket enkel </strong><strong>form </strong>några företeelser i olika sammanhang och områden där språket används, och kan då också göra enkla jämförelser med egna erfarenheter och kunskaper.</p>""",
                GradeStep.D to """<h3>Kunskapskrav för betyget D i slutet av årskurs 9</h3><p>Betyget D innebär att kunskapskraven för betyget E och till övervägande del för C är uppfyllda.</p>""",
                GradeStep.C to """<h3>Kunskapskrav för betyget C i slutet av årskurs 9</h3><p>Eleven kan förstå <strong>det </strong><strong>huvudsakliga innehållet och uppfatta tydliga detaljer</strong> i tydligt talat, enkelt språk i långsamt tempo samt i korta, enkla texter om vardagliga och välbekanta ämnen. Eleven visar sin förståelse genom att<strong> i mycket enkel form </strong>redogöra för innehåll och detaljer samt genom att med<strong> tillfredsställande </strong>resultat agera utifrån budskap och instruktioner i innehållet. För att underlätta sin förståelse av innehållet i det talade språket och texterna kan eleven<strong> i viss utsträckning</strong> välja och använda sig av <strong>strategier </strong>för lyssnande och läsning. Eleven kan välja texter och talat språk av enkel karaktär och från olika medier samt<strong> på ett relevant sätt</strong> använda det valda materialet i sin egen produktion och interaktion.</p><p>I muntliga och skriftliga framställningar av olika slag kan eleven formulera sig <strong>enkelt och</strong><strong><strong> r</strong>elativt tydligt</strong> <strong>med fraser och meningar</strong>. För att förtydliga och variera sin kommunikation kan eleven bearbeta och göra <strong>enstaka enkla</strong> förbättringar av egna framställningar. I muntlig och skriftlig interaktion kan eleven uttrycka sig <strong>enkelt och</strong><strong> begripligt med ord, fraser och meningar</strong>. Dessutom kan eleven välja och använda sig av <strong>några</strong> <strong>olika </strong><strong>strategier </strong>som löser problem i och förbättrar interaktionen.</p><p>Eleven kommenterar <strong>i</strong><strong> mycket enkel </strong><strong>form </strong>några företeelser i olika sammanhang och områden där språket används, och kan då också göra enkla jämförelser med egna erfarenheter och kunskaper.</p>""",
                GradeStep.B to """<h3>Kunskapskrav för betyget B i slutet av årskurs 9</h3><p>Betyget B innebär att kunskapskraven för betyget C och till övervägande del för A är uppfyllda.</p>""",
                GradeStep.A to """<h3>Kunskapskrav för betyget A i slutet av årskurs 9</h3><p>Eleven kan förstå <strong>det </strong><strong>huvudsakliga innehållet och uppfatta väsentliga detaljer</strong> i tydligt talat, enkelt språk i långsamt tempo samt i korta, enkla texter om vardagliga och välbekanta ämnen. Eleven visar sin förståelse genom att <strong>i</strong><strong> enkel </strong><strong>form </strong>redogöra för innehåll och detaljer samt genom att med <strong>gott </strong>resultat agera utifrån budskap och instruktioner i innehållet. För att underlätta sin förståelse av innehållet i det talade språket och texterna kan eleven <strong>i viss utsträckning</strong> välja och använda sig av <strong>strategier </strong>för lyssnande och läsning. Eleven kan välja bland texter och talat språk av enkel karaktär och från olika medier samt <strong>på ett relevant </strong><strong>och effektivt </strong><strong>sätt</strong> använda det valda materialet i sin egen produktion och interaktion.</p><p>I muntliga och skriftliga framställningar av olika slag kan eleven formulera sig <strong>enkelt,</strong><strong> relativt tydligt och till viss del sammanhängande</strong>. För att förtydliga och variera sin kommunikation kan eleven bearbeta och göra <strong>enkla</strong> förbättringar av egna framställningar. I muntlig och skriftlig interaktion kan eleven uttrycka sig <strong>enkelt och</strong><strong> tydligt med ord, fraser och meningar</strong>. Dessutom kan eleven välja och använda sig av <strong>flera olika</strong> <strong>strategier </strong>som löser problem i och förbättrar interaktionen.</p><p>Eleven kommenterar <strong>i</strong> <strong>enkel</strong> <strong>form </strong>några företeelser i olika sammanhang och områden där språket används, och kan då också göra enkla jämförelser med egna erfarenheter och kunskaper.</p>"""),
                courseDataExtractorMod.getKnowledgeRequirements(4..9, "WITHIN_STUDENT_CHOICE").first().knowledgeRequirements)

    }

    @Test
    fun testGetCourses() {
        assertArrayEquals(arrayListOf(
                CompulsoryCourseDataExtractor.CourseCondition("4-9", "WITHIN_STUDENT_CHOICE"),
                CompulsoryCourseDataExtractor.CourseCondition("4-9", "WITHIN_LANGUAGE_CHOICE"),
                CompulsoryCourseDataExtractor.CourseCondition("4-9", "WITHIN_STUDENT_CHOICE_CHINESE"),
                CompulsoryCourseDataExtractor.CourseCondition("4-9", "WITHIN_LANGUAGE_CHOICE_CHINESE")
                ).toTypedArray(),
                courseDataExtractorMod.getCoursesConditions().toTypedArray())
    }

    @Test
    fun testRangeConverter() {
        assertEquals(stringToRange("1-3"), 1..3)
        assertEquals(stringToRange("0-3"), 0..3)
        assertEquals(stringToRange("0 - 3"), 0..3)
        assertEquals(stringToRange(" 0 - 3 "), 0..3)


    }

    @Test(expected = NumberFormatException::class)
    fun testRangeConverterException() {
        stringToRange("3")
    }
    @Test(expected = NumberFormatException::class)
    fun testRangeConverterExceptionInvalidRange() {
        stringToRange("x-3")
    }
}

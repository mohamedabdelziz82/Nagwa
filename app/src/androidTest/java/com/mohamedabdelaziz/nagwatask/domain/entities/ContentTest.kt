package com.mohamedabdelaziz.nagwatask.domain.entities


import org.junit.Assert
import org.junit.Test
class ContentTest {
    private val content = Content(1,"name",ContentType.VIDEO,"url")

    @Test
    fun getId()
       {
           Assert.assertEquals(content.id, 1)
        }

    @Test
    fun getName() {
        Assert.assertEquals(content.name, "name")
        }

    @Test
    fun getContentType() {
            Assert.assertEquals(content.type, ContentType.VIDEO)
        }

    @Test
    fun getUrl() {
            Assert.assertEquals(content.url,"url")
        }

}
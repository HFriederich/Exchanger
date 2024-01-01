package com.db.systel.exchanger.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class ReaderTest {
    @Test
    fun writeFileLineByLineTest() {
        val reader = Reader()
        reader.writeFileLineByLine("src/test/resources/application-test.yml")
    }

    @Test
    fun readYamlTest() {
        val reader = Reader()
        println(reader.readYaml("src/test/resources/encode-test.yml"))
    }

    @Test
    fun readFileTest() {
        val reader = Reader()
        reader.readFile("src/test/resources/encode-test.yml","DecodedCipher")
    }

    @Test
    fun readFileAsLinesTest() {
        val reader = Reader()
        val lines = reader.readFileAsLines("src/test/resources/application-test.yml")
        assertTrue(lines.size > 3)
    }

    @Test
    fun matchCipherTest() {
        val reader = Reader()
        assertEquals("1234e45da1",reader.matchCipher("    password: '{cipher}1234e45da1'"))
        assertTrue(reader.matchCipher("    wrong: '{error}1234e45da1'").isEmpty())
    }

    @Test
    fun replaceCipherTest() {
        val reader = Reader()
        val resultLine = reader.replaceCipher("    password: '{cipher}1234e45da1'","DecodedString")
        assertEquals("    password: DecodedString", resultLine)
    }
}

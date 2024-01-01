package com.db.systel.exchanger.service

import org.yaml.snakeyaml.Yaml
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class Reader {

    fun readYaml(fileName: String) {
        val inputStream : InputStream = FileInputStream(File(fileName))
        val yaml = Yaml()
        val data :Map<String, Any> = yaml.load(inputStream)
        println(data)
    }

    fun readFile(fileName: String, valueToEncode: String) {
        val file = File(fileName)
        val lines = ArrayList(file.readLines())
        lines.forEachIndexed{index, line ->
            if (line.contains(valueToEncode)) {
                val cipheredValue = "'{cipher}1234e45da2'"
                val exchange = line.replace(valueToEncode, cipheredValue)
                println(exchange)
                val position = index + 1
                println("at position ${position}")
            } else {
                //println(line)
            }
        }
        /*
        lines.forEach{
            if (it.contains(valueToEncode)) {
                val cipheredValue = "'{cipher}1234e45da2'"
                val exchange = it.replace(valueToEncode, cipheredValue)
                println(exchange)
            } else {
                println(it)
            }
        }
        */
    }

    fun writeFileLineByLine(fileName: String) {
        val reader = Reader()
        val lines = reader.readFileAsLines(fileName)
        val resultFile = fileName.replace(".yml","-result.yml")
        File(resultFile).bufferedWriter().use {
            out ->
            lines.forEach {
                if (isComment(it)) {
                    out.writeLn(it)
                } else {
                    detectCipher(it, out)
                }
            }

        }

    }

    private fun detectCipher(it: String, out: BufferedWriter) {
        val cipher = matchCipher(it)
        if (cipher.isEmpty()) {
            out.writeLn(it)
        } else {
            val decodedValue = decodedCipher(cipher)
            if (decodedValue.isEmpty()) {
                out.writeLn(it)
            } else {
                out.writeLn(replaceCipher(it, decodedValue))
            }
        }
    }

    fun BufferedWriter.writeLn(line: String) {
        this.write(line)
        this.newLine()
    }

    fun decodedCipher(cipher: String): String {
        return "DecodedCipher"
    }
    
    fun readFileAsLines(fileName: String): List<String>
            = File(fileName).useLines{it.toList()}

    fun matchCipher(line: String): String {
        val pattern = """'\{cipher\}(.*)'""".toRegex()
        val match = pattern.find(line)
        var value = ""
        if (match != null) {
            if (match.groupValues.size > 1) value = match.groupValues[1]
        }
        return value
    }

    fun replaceCipher(line: String, decodedString: String): String {
        val pattern = """'\{cipher\}(.*)'""".toRegex()
        return line.replace(pattern, decodedString)
    }

    private fun isComment(value: String) = value.trim().startsWith("#")


}

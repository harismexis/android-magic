package com.harismexis.magic.parser

abstract class BaseFileParser {

    abstract fun getFileAsString(filePath: String): String

}
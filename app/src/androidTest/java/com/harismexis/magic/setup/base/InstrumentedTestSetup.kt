package com.harismexis.magic.setup.base

import com.harismexis.magic.parser.MockHerosParser
import com.harismexis.magic.setup.testutil.InstrumentedFileParser

open class InstrumentedTestSetup {
    protected val fileParser = InstrumentedFileParser()
    protected val herosParser = MockHerosParser(fileParser)
}
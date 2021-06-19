package com.harismexis.magic.setup.base

import com.harismexis.magic.parser.MockCardsParser
import com.harismexis.magic.setup.testutil.InstrumentedFileParser

open class InstrumentedTestSetup {
    protected val fileParser = InstrumentedFileParser()
    protected val herosParser = MockCardsParser(fileParser)
}
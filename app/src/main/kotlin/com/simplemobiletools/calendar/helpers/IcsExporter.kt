package com.simplemobiletools.calendar.helpers

import android.content.Context
import com.simplemobiletools.calendar.extensions.writeLn
import com.simplemobiletools.calendar.helpers.IcsExporter.ExportResult.*
import java.io.File

class IcsExporter {
    enum class ExportResult {
        EXPORT_FAIL, EXPORT_OK, EXPORT_PARTIAL
    }

    var eventsExported = 0
    var eventsFailed = 0

    fun exportEvents(context: Context, path: String): ExportResult {
        File(path, "events_${System.currentTimeMillis() / 1000}.ics").bufferedWriter().use { out ->
            out.writeLn(BEGIN_CALENDAR)
            out.writeLn(END_CALENDAR)
        }

        return if (eventsExported == 0) {
            EXPORT_FAIL
        } else if (eventsFailed > 0) {
            EXPORT_PARTIAL
        } else {
            EXPORT_OK
        }
    }
}

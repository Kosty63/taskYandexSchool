package prosolupov.konstantin.ru.taskyandexschool.model

import java.util.*

data class Items (
        var name: String,
        var preview: String,
        var created: Date,
        var modified: Date,
        var path: String,
        var md5: String,
        var type: String,
        var mime_type: String,
        var size: Int
)
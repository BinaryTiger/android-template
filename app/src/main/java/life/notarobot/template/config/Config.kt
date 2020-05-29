package life.notarobot.template.config

const val DATABASE_NAME_KEY: String = "database_name"

private const val DATABASE_NAME_VALUE: String = "persistence-db"

val config = mapOf(
    DATABASE_NAME_KEY to DATABASE_NAME_VALUE
)
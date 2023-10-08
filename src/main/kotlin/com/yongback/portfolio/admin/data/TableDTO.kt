package com.yongback.portfolio.admin.data

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

data class TableDTO(
    val name: String,
    val columns: List<String>,
    val records: List<List<String>>
) {
    companion object {
        fun <T : Any> from(classInfo: KClass<T>, entities: List<Any>, vararg filterings: String)
                : TableDTO {
            val name = classInfo.simpleName ?: "Unknown"
            val columns = createColumns(classInfo, *filterings)
            val records = entities.map { entity ->
                columns.map { column ->
                    classInfo.memberProperties
                        .find { column.equals(it.name) }
                        ?.getter
                        ?.call(entity)
                        .toString()
                }.toList()
            }.toList()

            return TableDTO(name = name, columns = columns, records = records)
        }

        private fun <T : Any> createColumns(classInfo: KClass<T>, vararg filterings: String)
                : MutableList<String> {
            val mainColumns = classInfo.java.declaredFields
                .filter { !filterings.contains(it.name) }
                .map { it.name }
                .toMutableList()
            val baseColumns = classInfo.java.superclass.declaredFields
                .map { it.name }
                .toMutableList()

            return (mainColumns + baseColumns).toMutableList()
        }
    }
}

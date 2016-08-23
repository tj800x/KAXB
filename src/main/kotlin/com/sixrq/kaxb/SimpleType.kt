package com.sixrq.kaxb

class SimpleType(xmlns: String, val packageName: String) : Tag(xmlns) {
    override fun toString(): String{
        if (children.filter { it.children.isNotEmpty() && it.children.filter { it is Enumeration }.isNotEmpty() }.isNotEmpty()) {
            return processEnumerationClass()
        }
        return ""
    }

    private fun processEnumerationClass(): String {
        val classDef = StringBuilder()
        val documentation = StringBuilder()
        val members : MutableList<Tag> = mutableListOf()

        children.filter { it is Annotation }.forEach {
            it.children.filter{ it is Documentation}.forEach { documentation.append("${it.toString()}\n")}
        }

        children.filter{ restriction -> restriction is Restriction }.forEach { member -> members.addAll(member.children.filter { enum -> enum is Enumeration }) }

        classDef.append("$packageName\n\n")
        classDef.append("import javax.xml.bind.annotation.XmlEnum\n")
        classDef.append("import javax.xml.bind.annotation.XmlEnumValue\n")
        classDef.append("import javax.xml.bind.annotation.XmlType\n")

        if(documentation.isNotBlank()) {
            classDef.append("\n$documentation\n")
        }
        classDef.append("\n@XmlType(name = \"$elementName\", namespace = \"$xmlns\")")
        classDef.append("\n@XmlEnum")
        classDef.append("\nenum class $name(${appendType()}) {\n")
        members.forEach { member ->
            classDef.append("$member\n")
        }
        classDef.setLength(classDef.length-2)
        classDef.append(";\n")

        if (appendType().isNotEmpty()) {
            classDef.append("\n    companion object {\n")
            classDef.append("        fun fromValue(${appendType().replace("val ", "")}): $name = $name.values().first { it.value == value }\n")
            classDef.append("    }\n")
        }
        classDef.append("}\n")
        return classDef.toString()

    }

    private fun appendType() : String {
        val restriction = (children.filter { it is Restriction })[0] as Restriction
        if (restriction.type.isNotBlank()) {
            return "val value : ${restriction.extractType()} "
        }
        return ""
    }

}

package com.sixrq.kaxb

import org.w3c.dom.Node

open class Tag() {
    var name: String = ""
    var elementName = ""
    var type: String = ""
    var minOccurs: String = ""
    var maxOccurs: String = ""

    val children: MutableList<Tag> = mutableListOf()

    open fun processAttributes(item: Node?) {
        if (item!!.attributes.getNamedItem("name") != null) {
            name = extractClassName(item.attributes.getNamedItem("name")!!.nodeValue)
            elementName = item.attributes.getNamedItem("name")!!.nodeValue
        }
        if (item!!.attributes.getNamedItem("type") != null) {
            type = extractClassName(item.attributes.getNamedItem("type")!!.nodeValue)
        }
        if (item!!.attributes.getNamedItem("minOccurs") != null) {
            minOccurs = item!!.attributes.getNamedItem("minOccurs").nodeValue
        }
        if (item!!.attributes.getNamedItem("maxOccurs") != null) {
            maxOccurs = item!!.attributes.getNamedItem("maxOccurs").nodeValue
        }
    }

    open fun processText(item: Node) {}

    fun extractClassName(name: String): String {
        val className = StringBuilder()
        name.split('_').forEach {
            className.append(it.capitalize())
        }
        return className.toString()
    }

    override fun toString(): String{
        return "$children"
    }
}

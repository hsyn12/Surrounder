package com.tr.xyz.surrounder.config

import com.intellij.openapi.components.*
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "SurroundSettings",
    storages = [Storage("surroundSettings.xml")]
)
class SurroundSettings : PersistentStateComponent<SurroundSettings> {
    var surrounder1Prefix: String = "`"
    var surrounder1Suffix: String = "`"
    var surrounder2Prefix: String = "'"
    var surrounder2Suffix: String = "'"
    var surrounder3Prefix: String = "```"
    var surrounder3Suffix: String = "```"

    override fun getState(): SurroundSettings = this

    override fun loadState(state: SurroundSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(): SurroundSettings = service<SurroundSettings>()
    }
}
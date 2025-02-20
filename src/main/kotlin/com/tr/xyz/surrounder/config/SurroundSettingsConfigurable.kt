package com.tr.xyz.surrounder.config

import com.intellij.openapi.options.Configurable
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JTextField

class SurroundSettingsConfigurable : Configurable {
    private var surrounder1PrefixField: JTextField? = null
    private var surrounder1SuffixField: JTextField? = null
    private var surrounder2PrefixField: JTextField? = null
    private var surrounder2SuffixField: JTextField? = null
    private var surrounder3PrefixField: JTextField? = null
    private var surrounder3SuffixField: JTextField? = null

    override fun getDisplayName(): String = "Text Surrounder Settings"

    override fun createComponent(): JComponent {
        surrounder1PrefixField = JTextField()
        surrounder1SuffixField = JTextField()
        surrounder2PrefixField = JTextField()
        surrounder2SuffixField = JTextField()
        surrounder3PrefixField = JTextField()
        surrounder3SuffixField = JTextField()

        val formBuilder = FormBuilder.createFormBuilder()
            .addLabeledComponent("Surrounder 1 Prefix:", surrounder1PrefixField!!)
            .addLabeledComponent("Surrounder 1 Suffix:", surrounder1SuffixField!!)
            .addSeparator()
            .addLabeledComponent("Surrounder 2 Prefix:", surrounder2PrefixField!!)
            .addLabeledComponent("Surrounder 2 Suffix:", surrounder2SuffixField!!)
            .addSeparator()
            .addLabeledComponent("Surrounder 3 Prefix:", surrounder3PrefixField!!)
            .addLabeledComponent("Surrounder 3 Suffix:", surrounder3SuffixField!!)

        return formBuilder.panel
    }

    override fun isModified(): Boolean {
        val settings = SurroundSettings.getInstance()
        return surrounder1PrefixField?.text != settings.surrounder1Prefix ||
                surrounder1SuffixField?.text != settings.surrounder1Suffix ||
                surrounder2PrefixField?.text != settings.surrounder2Prefix ||
                surrounder2SuffixField?.text != settings.surrounder2Suffix ||
                surrounder3PrefixField?.text != settings.surrounder3Prefix ||
                surrounder3SuffixField?.text != settings.surrounder3Suffix
    }

    override fun apply() {
        val settings = SurroundSettings.getInstance()
        settings.surrounder1Prefix = surrounder1PrefixField?.text ?: ""
        settings.surrounder1Suffix = surrounder1SuffixField?.text ?: ""
        settings.surrounder2Prefix = surrounder2PrefixField?.text ?: ""
        settings.surrounder2Suffix = surrounder2SuffixField?.text ?: ""
        settings.surrounder3Prefix = surrounder3PrefixField?.text ?: ""
        settings.surrounder3Suffix = surrounder3SuffixField?.text ?: ""
    }

    override fun reset() {
        val settings = SurroundSettings.getInstance()
        surrounder1PrefixField?.text = settings.surrounder1Prefix
        surrounder1SuffixField?.text = settings.surrounder1Suffix
        surrounder2PrefixField?.text = settings.surrounder2Prefix
        surrounder2SuffixField?.text = settings.surrounder2Suffix
        surrounder3PrefixField?.text = settings.surrounder3Prefix
        surrounder3SuffixField?.text = settings.surrounder3Suffix
    }
}
package com.tr.xyz.surrounder

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.tr.xyz.surrounder.config.SurroundSettings

/**
 * Created by hsyntr on 2025-02-20
 */
abstract class BaseSurroundAction : AnAction() {
    abstract fun getPrefix(): String
    abstract fun getSuffix(): String

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)

        e.presentation.isEnabled = project != null &&
                editor != null /*&&
                editor.selectionModel.hasSelection()*/
    }

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        val document = editor.document
        val selectionModel = editor.selectionModel

        val selectedText = selectionModel.selectedText
        val start = selectionModel.selectionStart
        val end = selectionModel.selectionEnd

        // if there is a selection, surround it
        if (selectedText != null && selectedText.isNotBlank())
            WriteCommandAction.runWriteCommandAction(project) {
                val newText = "${getPrefix()}$selectedText${getSuffix()}"
                document.replaceString(start, end, newText)
                selectionModel.setSelection(
                    start + getPrefix().length,
                    start + newText.length - getSuffix().length
                )
                editor.caretModel.moveToOffset(start + getPrefix().length)
            }
        else {
            val _start = editor.caretModel.offset
            val newText = "${getPrefix()}${getSuffix()}"
            WriteCommandAction.runWriteCommandAction(project) {
                document.insertString(_start, newText)
                editor.caretModel.moveToOffset(_start + getPrefix().length)
            }

        }
    }

    override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT
}

class Surrounder1Action : BaseSurroundAction() {
    override fun getPrefix() = SurroundSettings.getInstance().surrounder1Prefix
    override fun getSuffix() = SurroundSettings.getInstance().surrounder1Suffix
}

class Surrounder2Action : BaseSurroundAction() {
    override fun getPrefix() = SurroundSettings.getInstance().surrounder2Prefix
    override fun getSuffix() = SurroundSettings.getInstance().surrounder2Suffix
}

class Surrounder3Action : BaseSurroundAction() {
    override fun getPrefix() = SurroundSettings.getInstance().surrounder3Prefix
    override fun getSuffix() = SurroundSettings.getInstance().surrounder3Suffix
}
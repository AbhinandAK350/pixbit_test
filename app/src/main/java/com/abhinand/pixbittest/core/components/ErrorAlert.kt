package com.abhinand.pixbittest.core.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary

@Composable
fun ErrorAlert(text: String, onDismiss: () -> Unit) {
    AlertDialog(
        containerColor = Color(0xFFFBFDFF),
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(R.string.error), color = Primary) },
        text = { Text(text = text) },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(R.string.ok), color = Secondary)
            }
        }
    )
}
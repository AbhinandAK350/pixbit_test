package com.abhinand.pixbittest.add_employee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.core.theme.interMedium
import com.abhinand.pixbittest.core.theme.interSemiBold

@Composable
fun StepIndicator(step: String, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE2F1FF))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = step,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            fontFamily = interSemiBold,
            letterSpacing = 0.sp
        )
        Text(
            text = title, fontSize = 14.sp,
            color = Color(0xFF2979FF),
            letterSpacing = 0.sp,
            fontWeight = FontWeight.W500,
            fontFamily = interMedium
        )
    }
}
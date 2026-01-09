package com.abhinand.pixbittest.add_employee.presentation.components.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.add_employee.presentation.components.StepIndicator
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.theme.interRegular
import com.abhinand.pixbittest.core.theme.interSemiBold

@Composable
fun SalarySchemeStep(modifier: Modifier = Modifier, onFinish: () -> Unit) {

    var selectedPeriod by rememberSaveable { mutableStateOf(3) }
    var salary by rememberSaveable { mutableFloatStateOf(30000f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    ) {
        StepIndicator(step = "Step 3/3", title = "Salary Scheme")

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {

            Text(
                text = "Contract Period",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ContractChip(
                    text = "3 Months",
                    selected = selectedPeriod == 3,
                    onClick = { selectedPeriod = 3 }
                )
                ContractChip(
                    text = "6 Months",
                    selected = selectedPeriod == 6,
                    onClick = { selectedPeriod = 6 }
                )
                ContractChip(
                    text = "12 Months",
                    selected = selectedPeriod == 12,
                    onClick = { selectedPeriod = 12 }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total Salary",
                    fontSize = 14.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight.W600
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFE9F3FF))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "₹ ${salary.toInt()}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = interRegular,
                        letterSpacing = 0.sp,
                        color = Color(0xFF2979FF)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Slider(
                value = salary,
                onValueChange = { salary = it },
                valueRange = 10_000f..200_000f
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "₹ 10,000",
                    color = Color(0xFFB1B1B1),
                    fontSize = 14.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.sp
                )
                Text(
                    text = "₹ 2,00,000",
                    color = Color(0xFFB1B1B1),
                    fontSize = 14.sp,
                    fontFamily = interSemiBold,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Monthly Payment (1/3)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Remaining",
                    color = Color(0xFF2979FF),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                InfoChip("₹ ${salary.toInt()}")
                InfoChip("100%")
                InfoChip("$selectedPeriod Month")
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun ContractChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(43.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 0.5.dp,
                color = if (selected) Color(0xFF2979FF) else Color(0xFFBFD9FF),
                shape = RoundedCornerShape(10.dp)
            )
            .background(if (selected) Color(0xFFE9F3FF) else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = if (selected) Color(0xFF2979FF) else Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun InfoChip(text: String) {
    Box(
        modifier = Modifier
            .padding(start = 6.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE9F3FF))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFF2979FF),
            fontWeight = FontWeight.Medium
        )
    }
}
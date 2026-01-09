package com.abhinand.pixbittest.add_employee.presentation.components.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.add_employee.presentation.components.PrimaryButton
import com.abhinand.pixbittest.add_employee.presentation.components.StepIndicator
import com.abhinand.pixbittest.core.theme.Secondary

@Composable
fun BasicDetailsStep(onNext: () -> Unit) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    ) {
        StepIndicator(step = "Step 1/3", title = "Basic Details")

        Spacer(modifier = Modifier.height(27.dp))

        ProfileImagePicker()

        Spacer(modifier = Modifier.height(27.dp))

        LabeledTextField(label = "First Name", placeholder = stringResource(R.string.enter_name))

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(label = "Last Name", placeholder = stringResource(R.string.enter_name))

        Spacer(modifier = Modifier.height(16.dp))

        DatePickerField(label = "Date of Birth", value = "01/01/2000")

        Spacer(modifier = Modifier.height(16.dp))

        DropdownField(label = "Gender", placeholder = "Select Gender")

        Spacer(modifier = Modifier.height(16.dp))

        DropdownField(label = "Marital Status", placeholder = "Select Marital Status")

        Spacer(modifier = Modifier.height(27.dp))

        PrimaryButton(text = "Next", onClick = onNext)

        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun ProfileImagePicker() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFE6E6E6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFF9E9E9E),
                modifier = Modifier.size(56.dp)
            )
        }

        Box(
            modifier = Modifier
                .offset(x = 36.dp, y = 36.dp)
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFE9F3FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Color(0xFF2979FF),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun LabeledTextField(
    label: String,
    placeholder: String
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A1A1A)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 0.5.dp,
                    color = Secondary,
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true
        )
    }
}

@Composable
fun DatePickerField(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color(0xFF2979FF)
                )
            }
        )
    }
}

@Composable
fun DropdownField(
    label: String,
    placeholder: String
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text(placeholder) },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFF2979FF)
                )
            }
        )
    }
}

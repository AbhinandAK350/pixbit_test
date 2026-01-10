package com.abhinand.pixbittest.add_employee.presentation.components.steps

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.add_employee.presentation.components.PrimaryButton
import com.abhinand.pixbittest.add_employee.presentation.components.StepIndicator
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.theme.interRegular
import com.abhinand.pixbittest.core.theme.interSemiBold

@Composable
fun BasicDetailsStep(
    imageUri: Uri?,
    onImageClick: () -> Unit,
    onNext: () -> Unit,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    dob: String,
    onDatePickerClick: () -> Unit,
    gender: String,
    onGenderChange: (String) -> Unit,
    isGenderDropdownOpen: Boolean,
    onGenderDropdownOpenChange: (Boolean) -> Unit,
    genderOptions: List<String>,
    designation: String,
    onDesignationChange: (String) -> Unit,
    isDesignationDropdownOpen: Boolean,
    onDesignationDropdownOpenChange: (Boolean) -> Unit,
    designationOptions: List<String>
) {

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

        ProfileImagePicker(
            imageUri = imageUri,
            onImageClick = onImageClick
        )

        Spacer(modifier = Modifier.height(27.dp))

        LabeledTextField(
            label = "First Name",
            placeholder = stringResource(R.string.enter_name),
            value = firstName,
            onValueChange = onFirstNameChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = "Last Name",
            placeholder = stringResource(R.string.enter_name),
            value = lastName,
            onValueChange = onLastNameChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        DatePickerField(
            label = "Date of Birth",
            value = dob,
            onClick = onDatePickerClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        DropdownField(
            label = "Designation",
            placeholder = "Select One",
            value = designation,
            onValueChange = onDesignationChange,
            isOpen = isDesignationDropdownOpen,
            onOpenChange = onDesignationDropdownOpenChange,
            options = designationOptions
        )
        Spacer(modifier = Modifier.height(16.dp))

        DropdownField(
            label = "Gender",
            placeholder = "Select One",
            value = gender,
            onValueChange = onGenderChange,
            isOpen = isGenderDropdownOpen,
            onOpenChange = onGenderDropdownOpenChange,
            options = genderOptions
        )

        Spacer(modifier = Modifier.height(27.dp))

        PrimaryButton(text = "Next", onClick = onNext)

        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun ProfileImagePicker(
    imageUri: Uri?,
    onImageClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUri ?: R.drawable.img_employee_placeholder,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(125.dp)
                .clip(CircleShape)
        )

        Box(
            modifier = Modifier
                .offset(x = 44.dp, y = 44.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFFE9F3FF))
                .clickable { onImageClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = "Edit Profile Image",
                modifier = Modifier.size(44.dp)
            )
        }
    }
}

@Composable
fun LabeledTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            fontFamily = interSemiBold,
            letterSpacing = 0.sp,
            color = Primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    placeholder,
                    fontFamily = interRegular,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    letterSpacing = 0.sp,
                    color = Color(0xFFB1B1B1)
                )
            },
            textStyle = TextStyle(
                fontFamily = interRegular,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                letterSpacing = 0.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 0.5.dp,
                    color = Secondary,
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Secondary,
                unfocusedBorderColor = Secondary,
                focusedLabelColor = Secondary,
                cursorColor = Secondary,
                errorBorderColor = Color.Red
            )
        )
    }
}

@Composable
fun DatePickerField(
    label: String,
    value: String,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            fontFamily = interSemiBold,
            letterSpacing = 0.sp,
            color = Primary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                    )
                },
                textStyle = TextStyle(
                    fontFamily = interRegular,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    letterSpacing = 0.sp,
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Primary,
                    disabledContainerColor = Color.Transparent,
                    disabledBorderColor = Secondary,
                    disabledTrailingIconColor = Color(0xFF2979FF)
                )
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(onClick = onClick)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isOpen: Boolean,
    onOpenChange: (Boolean) -> Unit,
    options: List<String>
) {
    Column {

        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            fontFamily = interSemiBold,
            color = Primary
        )

        Spacer(modifier = Modifier.height(6.dp))

        ExposedDropdownMenuBox(
            expanded = isOpen,
            onExpandedChange = onOpenChange
        ) {
            val fillMaxWidth = Modifier
                .fillMaxWidth()
            OutlinedTextField(
                value = value,
                onValueChange = {},
                modifier = fillMaxWidth.menuAnchor(),
                readOnly = true,
                placeholder = {
                    Text(
                        text = placeholder,
                        fontFamily = interRegular,
                        fontSize = 14.sp,
                        color = Color(0xFFB1B1B1)
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isOpen)
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Secondary,
                    unfocusedBorderColor = Secondary
                )
            )

            ExposedDropdownMenu(
                containerColor = Container,
                expanded = isOpen,
                onDismissRequest = { onOpenChange(false) }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                option, fontFamily = interRegular,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp,
                                letterSpacing = 0.sp,
                            )
                        },
                        onClick = {
                            onValueChange(option)
                            onOpenChange(false)
                        }
                    )
                }
            }
        }
    }
}

package com.abhinand.pixbittest.add_employee.presentation.components.steps

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.add_employee.presentation.components.StepIndicator
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.theme.interRegular
import com.abhinand.pixbittest.core.utils.InputType

@Composable
fun ContactDetailsStep(
    mobileNumber: String,
    onMobileNumberChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    onNext: () -> Unit,
    isEmailValid: Boolean,
    emailTouched: Boolean,
    isContactNextButtonEnabled: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    )
    {
        StepIndicator(step = "Step 2/3", title = "Contact Details")

        Spacer(modifier = Modifier.height(27.dp))

        LabeledTextField(
            label = "Mobile Number",
            placeholder = stringResource(R.string.enter_mobile_number),
            value = mobileNumber,
            inputType = InputType.PHONE,
            onValueChange = onMobileNumberChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = "Email",
            placeholder = stringResource(R.string.enter_mail_address),
            value = email,
            inputType = InputType.EMAIL,
            onValueChange = onEmailChange
        )
        if (!isEmailValid && emailTouched) {
            Text(
                text = "Name should only contain alphabets and white space",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Text(
                text = "Address",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1A1A1A)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { onAddressChange(it) },
                placeholder = {
                    Text(
                        text = "Address",
                        fontFamily = interRegular,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        letterSpacing = 0.sp,
                        color = Color(0xFFB1B1B1)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .border(
                        width = 0.5.dp,
                        color = Secondary,
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
                singleLine = false,
                maxLines = 4,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Start,
                    fontFamily = interRegular,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    letterSpacing = 0.sp
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Secondary,
                    unfocusedBorderColor = Secondary,
                    focusedLabelColor = Secondary,
                    cursorColor = Secondary,
                    errorBorderColor = Color.Red
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            onClick = { onNext() },
            enabled = isContactNextButtonEnabled,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Secondary)
        ) {
            Text(text = "Next", fontWeight = FontWeight.W500, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(25.dp))
    }
}
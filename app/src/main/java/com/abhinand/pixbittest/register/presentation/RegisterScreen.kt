package com.abhinand.pixbittest.register.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.theme.TextFieldBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier.padding(start = 10.dp),
                    text = "Register",
                    fontWeight = FontWeight.W500,
                    color = Primary,
                    fontSize = 20.sp
                )
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = null
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Secondary)
        )
    }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }
            var confirmPasswordVisible by remember { mutableStateOf(false) }

            Text(
                text = stringResource(R.string.name),
                fontWeight = FontWeight.W600,
                color = Primary,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )
            Spacer(modifier = modifier.height(12.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = TextFieldBorder,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = { Text(stringResource(R.string.enter_name), letterSpacing = 0.sp) },
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.email),
                fontWeight = FontWeight.W600,
                color = Primary,
                fontSize = 14.sp
            )
            Spacer(modifier = modifier.height(12.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = TextFieldBorder,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_mail_address),
                        letterSpacing = 0.sp
                    )
                },
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Password", fontWeight = FontWeight.W600, color = Primary, fontSize = 14.sp)
            Spacer(modifier = modifier.height(12.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = TextFieldBorder,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_password),
                        letterSpacing = 0.sp
                    )
                },
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.confirm_password),
                fontWeight = FontWeight.W600,
                color = Primary,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )
            Spacer(modifier = modifier.height(12.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = TextFieldBorder,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_password),
                        letterSpacing = 0.sp
                    )
                },
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description =
                        if (confirmPasswordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A90E2)
                )
            ) {
                Text(
                    text = stringResource(R.string.register), letterSpacing = 0.sp,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
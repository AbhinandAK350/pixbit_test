package com.abhinand.pixbittest.register.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Action) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier.padding(start = 10.dp),
                    text = stringResource(R.string.register),
                    fontWeight = FontWeight.W500,
                    color = Primary,
                    fontSize = 20.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = { onNavigate(Action.Pop) }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Container)
        )
    }, containerColor = Color(0xFFFBFDFF)) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.name),
                fontWeight = FontWeight.W600,
                color = Primary,
                fontSize = 14.sp,
                letterSpacing = 0.sp
            )
            Spacer(modifier = modifier.height(12.dp))
            OutlinedTextField(
                value = state.name,
                onValueChange = { viewModel.onNameChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = Secondary,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_name),
                        letterSpacing = 0.sp,
                        color = Color(0xFFB1B1B1)
                    )
                },
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
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = Secondary,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_mail_address),
                        letterSpacing = 0.sp,
                        color = Color(0xFFB1B1B1)
                    )
                },
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Password", fontWeight = FontWeight.W600, color = Primary, fontSize = 14.sp)
            Spacer(modifier = modifier.height(12.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = Secondary,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_password),
                        letterSpacing = 0.sp,
                        color = Color(0xFFB1B1B1)
                    )
                },
                trailingIcon = {
                    val image = if (state.isPasswordVisible)
                        R.drawable.ic_eye_open
                    else R.drawable.ic_eye_close

                    IconButton(
                        onClick = { viewModel.onPasswordVisibilityChange() }) {
                        Icon(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(image),
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                value = state.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 0.5.dp,
                        color = Secondary,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_password),
                        letterSpacing = 0.sp,
                        color = Color(0xFFB1B1B1)
                    )
                },
                trailingIcon = {
                    val image = if (state.isConfirmPasswordVisible)
                        R.drawable.ic_eye_open
                    else R.drawable.ic_eye_close

                    IconButton(
                        onClick = { viewModel.onConfirmPasswordVisibilityChange() }) {
                        Icon(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(image),
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (state.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.onRegisterButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = state.isRegisterButtonEnabled,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary
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
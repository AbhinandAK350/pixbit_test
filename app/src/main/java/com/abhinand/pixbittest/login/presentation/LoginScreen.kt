package com.abhinand.pixbittest.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.abhinand.pixbittest.core.components.ErrorAlert
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.navigation.Screen
import com.abhinand.pixbittest.core.theme.interMedium
import com.abhinand.pixbittest.core.theme.interRegular
import com.abhinand.pixbittest.core.theme.interSemiBold

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Action) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.errorMessage != null) {
        ErrorAlert(state.errorMessage ?: "Unknown error") { viewModel.dismissErrorDialog() }
    }

    Scaffold(containerColor = Color(0xFFFBFDFF)) { contentPadding ->

        Column(modifier = Modifier.padding(top = contentPadding.calculateTopPadding())) {

            Spacer(modifier = modifier.weight(0.1f))

            Column(modifier = Modifier.padding(start = 36.dp)) {
                Text(
                    text = stringResource(R.string.welcome_back),
                    fontFamily = interSemiBold,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W600,
                    letterSpacing = 0.sp,
                    color = Color(0xFF153F67)
                )

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.please_login_to_your_account),
                    fontFamily = interRegular,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.sp,
                    color = Color((0xFF2A5277))
                )
            }

            Spacer(modifier = modifier.weight(0.1f))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE4F1F6)
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = state.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        placeholder = {
                            Text(
                                stringResource(R.string.email),
                                color = Color(0xFF56728C),
                                fontFamily = interMedium
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = null
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),
                        placeholder = {
                            Text(
                                stringResource(R.string.password),
                                color = Color(0xFF56728C),
                                fontFamily = interMedium
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { viewModel.onPasswordVisibilityChange() }) {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = if (state.isPasswordVisible)
                                        painterResource(R.drawable.ic_eye_open)
                                    else
                                        painterResource(R.drawable.ic_eye_close),
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (state.isPasswordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { if (!state.isLoading) viewModel.onLoginClick() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = state.isLoginButtonEnabled,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3485D1)
                        )
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                strokeWidth = 3.dp,
                                modifier = Modifier.size(24.dp),
                                color = Color.White
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.sign_in),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { onNavigate(Action.Push(Screen.Register)) }) {
                        Text(
                            text = stringResource(R.string.register_now),
                            color = Color(0xFF153F67),
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp
                        )
                    }
                }
            }

        }

    }

}

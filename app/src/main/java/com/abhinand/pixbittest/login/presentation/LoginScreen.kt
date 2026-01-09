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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.theme.interRegular
import com.abhinand.pixbittest.core.theme.interSemiBold

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    Scaffold { contentPadding ->

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
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var passwordVisible by remember { mutableStateOf(false) }

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White),
                        placeholder = { Text(stringResource(R.string.email)) },
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
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),
                        placeholder = { Text(stringResource(R.string.password)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = if (passwordVisible)
                                        painterResource(R.drawable.ic_eye_open)
                                    else
                                        painterResource(R.drawable.ic_eye_close),
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible)
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
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3485D1)
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.sign_in),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { /*TODO*/ }) {
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
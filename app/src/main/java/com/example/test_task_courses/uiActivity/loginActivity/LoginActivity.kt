package com.example.test_task_courses.uiActivity.loginActivity

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.test_task_courses.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()

    LaunchedEffect(key1 = viewModel.loginEvent) {
        viewModel.loginEvent.collectLatest {
            onLoginSuccess()
        }
    }

    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Вход",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                textAlign = TextAlign.Start
            )

            // Email Label
            Text(text = "Email", color = Color(0xFFF2F2F3), fontSize = 14.sp)
            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.onEmailChanged(it) },
                placeholder = { Text("example@gmail.com", color = Color(0xFFF2F2F3)) },
                singleLine = true,
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(Color(0xFF32333A), RoundedCornerShape(30.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF888888),
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color(0xFF12B956)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Password Label
            Text(text = "Пароль", color = Color(0xFFF2F2F3), fontSize = 14.sp)
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                placeholder = { Text("Введите пароль", color = Color(0xFFF2F2F3)) },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Close else Icons.Default.Done,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(Color(0xFF32333A), RoundedCornerShape(30.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF888888),
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color(0xFF12B956)
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Login button
            Button(
                onClick = { viewModel.onLoginClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF12B956) else Color(0xFF555555),
                    contentColor = Color.White,
                )
            ) {
                Text(text = "Вход", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Text(
                        text = "Нету аккаунта?",
                        color = Color(0xFFF2F2F3),
                        modifier = Modifier.clickable(enabled = false) { }
                    )
                    Text(
                        text = " Регистрация",
                        color = Color(0xFF12B956),
                        modifier = Modifier.clickable(enabled = false) { }
                    )
                }
                Text(
                    text = "Забыл пароль",
                    color = Color(0xFF12B956),
                    modifier = Modifier.clickable(enabled = false) { }
                )

            }


            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(thickness = 1.dp, color = Color(0xFF4D555E))
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SocialButton(
                    backgroundColor = Color(0xFF2683ED),
                    iconResId = R.drawable.vk_icon
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
                    context.startActivity(intent)
                }

                GradientButton(
                    gradient = Brush.verticalGradient(
                        colors = listOf(Color(0xFFF98509), Color(0xFFF95D00))
                    ),
                    iconResId = R.drawable.odnoklassniki_icon
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun SocialButton(
    backgroundColor: Color,
    iconResId: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(40.dp)
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(50.dp,40.dp),
            tint = Color.Unspecified
        )
    }
}

@Composable
fun GradientButton(
    gradient: Brush,
    iconResId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(brush = gradient)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(70.dp,40.dp),
                tint = Color.Unspecified
            )
        }
    }
}



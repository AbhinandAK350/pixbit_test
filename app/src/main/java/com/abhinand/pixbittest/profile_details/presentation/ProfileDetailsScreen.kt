package com.abhinand.pixbittest.profile_details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailsScreen(modifier: Modifier = Modifier, onNavigate: (Action) -> Unit) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier.padding(start = 10.dp),
                    text = stringResource(R.string.profile_details),
                    fontWeight = FontWeight.W500,
                    color = Primary,
                    fontSize = 20.sp
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Container),
            navigationIcon = {
                IconButton(onClick = { onNavigate(Action.Pop) }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
            },
        )
    }, containerColor = Color(0xFFFBFDFF)) { contentPadding ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding() + 20.dp,
                bottom = contentPadding.calculateBottomPadding() + 25.dp
            )
        ) {

            item {
                ProfileHeader()
            }

            item {
                ContactInfoCard()
            }

            item {
                ResumeCard()
            }

            item {
                SalarySchemeHeader()
            }

            item {
                SalaryMonthCard(
                    month = "Month 1",
                    date = "12-12-2024",
                    percentage = "20%",
                    amount = "₹ 12,000",
                    remarks = "First Salary"
                )
            }

            item {
                SalaryMonthCard(
                    month = "Month 2",
                    date = "12-01-2025",
                    percentage = "50%",
                    amount = "₹ 30,000",
                    remarks = "Secondary Salary"
                )
            }

            item {
                SalaryMonthCard(
                    month = "Month 3",
                    date = "12-02-2025",
                    percentage = "30%",
                    amount = "₹ 18,000",
                    remarks = "Final Salary"
                )
            }
        }

    }
}

@Composable
private fun ProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://picsum.photos/200",
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "John Joy",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Supervisor",
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}

@Composable
private fun ContactInfoCard() {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            TwoColumnRow("Contact Number", "+91 8412094567")
            TwoColumnRow("Email", "johnjoy@mail.com")

            Spacer(modifier = Modifier.height(12.dp))

            TwoColumnRow("Date of birth", "12-12-2022")
            TwoColumnRow("Gender", "Male")

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Address",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "12 street, The Marine World, los angeles",
                fontSize = 13.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
private fun ResumeCard() {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Description,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Resume File",
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Updated 01-01-2020",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            TextButton(onClick = {}) {
                Text("View")
            }
        }
    }
}

@Composable
private fun SalarySchemeHeader() {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9F3FF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Salary Scheme",
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "3 Months   ₹60,000",
                color = Color(0xFF2979FF),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun SalaryMonthCard(
    month: String,
    date: String,
    percentage: String,
    amount: String,
    remarks: String
) {
    Column {
        Text(
            text = month,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF2979FF)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                TwoColumnRow("Payment Date", date)
                TwoColumnRow("Payment Amount ($percentage)", amount)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Remarks",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = remarks,
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
private fun TwoColumnRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                fontSize = 13.sp,
                color = Color.DarkGray
            )
        }
    }
}